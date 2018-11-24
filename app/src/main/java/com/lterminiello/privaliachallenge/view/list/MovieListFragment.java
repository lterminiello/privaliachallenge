package com.lterminiello.privaliachallenge.view.list;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lterminiello.privaliachallenge.R;
import com.lterminiello.privaliachallenge.databinding.MovieListFragmentBinding;
import com.lterminiello.privaliachallenge.usecase.UseCaseFactory;
import com.lterminiello.privaliachallenge.utils.RecyclerViewScrollListener;
import com.lterminiello.privaliachallenge.view.MovieActivity;
import com.lterminiello.privaliachallenge.viewmodel.MovieViewModelFactory;
import com.lterminiello.privaliachallenge.viewmodel.list.MovieListViewModel;

public class MovieListFragment extends Fragment {

    public static final String TAG = "MovieListFragment";

    private MovieViewModelFactory movieViewModelFactory;
    private MovieListViewModel viewModel;

    private MovieListFragmentBinding binding;

    private MoviesAdapter adapter;


    public static MovieListFragment newInstance(MovieViewModelFactory movieViewModelFactory) {
        MovieListFragment fragment = new MovieListFragment();
        fragment.setMovieViewModelFactory(movieViewModelFactory);
        return fragment;
    }

    public void setMovieViewModelFactory(final MovieViewModelFactory movieViewModelFactory) {
        this.movieViewModelFactory = movieViewModelFactory;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        adapter = new MoviesAdapter(new UseCaseFactory());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment, container, false);

        ((MovieActivity) getActivity()).setSupportActionBar(binding.toolbar);

        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this, movieViewModelFactory).get(MovieListViewModel.class);
        binding.setViewModel(viewModel);
        viewModel.getMovies(null);
        subscriberObserver();

    }

    private void subscriberObserver() {
        viewModel.getMoviesData().observe(this, movies ->
            adapter.setResultList(movies)
        );

        //Todo ver que hago aca
        /*viewModel.getErrorMessage().observe(this, s -> {
            binding.imageView.setImageResource(R.drawable.ic_error);
            binding.loadingTv.setText(s);
        });*/

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

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onLoadMore() {
                viewModel.nextPage();
            }
        });

    }
}
