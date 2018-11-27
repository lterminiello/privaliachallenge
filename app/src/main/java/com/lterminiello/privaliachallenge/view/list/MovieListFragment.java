package com.lterminiello.privaliachallenge.view.list;

import android.app.ActionBar;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.lterminiello.privaliachallenge.R;
import com.lterminiello.privaliachallenge.TrackTvApplication;
import com.lterminiello.privaliachallenge.databinding.MovieListFragmentBinding;
import com.lterminiello.privaliachallenge.usecase.UseCaseFactory;
import com.lterminiello.privaliachallenge.utils.Lists;
import com.lterminiello.privaliachallenge.utils.RecyclerViewScrollListener;
import com.lterminiello.privaliachallenge.utils.SnackbarUtils;
import com.lterminiello.privaliachallenge.utils.StringUtils;
import com.lterminiello.privaliachallenge.utils.ViewUtils;
import com.lterminiello.privaliachallenge.viewmodel.MovieViewModelFactory;
import com.lterminiello.privaliachallenge.viewmodel.list.MovieListViewModel;

public class MovieListFragment extends Fragment {

    public static final String TAG = "MovieListFragment";
    public static final String QUERY = "query";

    private MovieListViewModel viewModel;

    private MovieListFragmentBinding binding;

    private MoviesAdapter adapter;

    private String query;

    public static MovieListFragment newInstance(MovieViewModelFactory movieViewModelFactory) {
        MovieListFragment fragment = new MovieListFragment();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState != null) {
            query = savedInstanceState.getString(QUERY);
        }
        adapter = new MoviesAdapter(new UseCaseFactory());
    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(QUERY, query);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment, container, false);

        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);

        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel =
            ViewModelProviders.of(this, TrackTvApplication.getMovieViewModelFactory()).get(MovieListViewModel.class);
        binding.setViewModel(viewModel);
        viewModel.getMovies(query);
        subscriberObserver();
    }

    private void subscriberObserver() {
        viewModel.getMoviesData().observe(this, movies -> {
            if (Lists.isNullOrEmpty(movies) && adapter.getItemCount() > 0) {

                return;
            }
            adapter.setResultList(movies);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError) {
                SnackbarUtils.makeSnackbarError(binding.getRoot(), R.string.errorInfo).show();
            }
        });

        viewModel.getLoadingPagination().observe(this, isLoading -> {
            if (isLoading) {
                adapter.initLoading();
            } else {
                adapter.dismissLoading();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    private void initRecyclerView() {
        binding.movieList.setAdapter(adapter);
        binding.movieList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.movieList.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        binding.movieList.addOnScrollListener(new RecyclerViewScrollListener() {
            @Override
            public void onScrollUp() {
                ViewUtils.hideKeyboard(getActivity(), binding.getRoot());
            }

            @Override
            public void onScrollDown() {
                ViewUtils.hideKeyboard(getActivity(), binding.getRoot());
            }

            @Override
            public void onLoadMore() {
                viewModel.nextPage();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list_toolbar, menu);
        MenuItem mSearch = menu.findItem(R.id.action_search);
        initSearchView(mSearch);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initSearchView(MenuItem mSearch) {
        SearchView searchView = (SearchView) mSearch.getActionView();
        searchView.setQueryHint(getString(R.string.searchLabel));
        searchView.setLayoutParams(new ActionBar.LayoutParams(Gravity.RIGHT));
        ImageView searchClose = searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        searchClose.setImageResource(R.drawable.ic_close);

        if (!StringUtils.isBlank(query)) {
            searchView.setQuery(query, true);
        }
        binding.toolbar.setOnClickListener(onClick -> {
            mSearch.expandActionView();
            searchView.setIconified(false);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                if (!newText.equals(query)) {
                    binding.movieList.scrollToPosition(0);
                    adapter.clearItems();
                    viewModel.getMovies(newText);
                    query = newText;
                }
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!StringUtils.isBlank(query) || !StringUtils.isBlank(newText)) {
                    binding.movieList.scrollToPosition(0);
                    adapter.clearItems();
                    viewModel.getMovies(newText);
                    query = newText;
                }
                return true;
            }
        });
    }
}
