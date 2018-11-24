package com.lterminiello.privaliachallenge.view.list;

import android.databinding.DataBindingUtil;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.lterminiello.privaliachallenge.R;
import com.lterminiello.privaliachallenge.databinding.MovieListItemBinding;

import com.lterminiello.privaliachallenge.databinding.ProgressItemBinding;
import com.lterminiello.privaliachallenge.model.Movie;
import com.lterminiello.privaliachallenge.usecase.UseCaseFactory;
import com.lterminiello.privaliachallenge.usecase.movie.ImageMovieUseCase;
import com.lterminiello.privaliachallenge.utils.Lists;
import com.lterminiello.privaliachallenge.viewmodel.list.MovieListItemViewModel;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private boolean loading;

    private List<Movie> resultList;

    private UseCaseFactory useCaseFactory;

    public MoviesAdapter(UseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_LOADING) {
            ProgressItemBinding progressItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.progress_item, parent, false);
            return new LoadingViewHolder(progressItemBinding);
        }
        MovieListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
            , R.layout.movie_list_item, parent, false);
       /*TODO if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {   ????????
            binding.productImage.setClipToOutline(true);
        }*/
        return new MovieViewHolder(binding, useCaseFactory.getImageMovieUseCase());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieViewHolder) {
            ((MovieViewHolder) holder).bindMoview(resultList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return resultList != null ? resultList.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(resultList.get(position).getIds().get("trakt"));

    }

    public void initLoading() {
        if (!loading && !Lists.isNullOrEmpty(resultList)) {
            resultList.add(null);
            loading = true;
            notifyItemInserted(resultList.size());
        }
    }

    public void dismissLoading() {
        if (loading && !Lists.isNullOrEmpty(resultList)) {
            loading = false;
            resultList.remove(resultList.size() - 1);
            notifyItemRemoved(resultList.size() + 1);
        }
    }

    public void setResultList(List<Movie> results) {
        if (results != null) {
            if (resultList == null) {
                resultList = results;
                notifyDataSetChanged();
            } else {
                int oldSize = getItemCount();
                resultList.addAll(results);
                notifyItemRangeChanged(oldSize, getItemCount());
            }
        }
    }

    public void clearItems() {
        if (resultList != null) {
            resultList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return resultList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        MovieListItemBinding movieListItemBinding;
        ImageMovieUseCase imageMovieUseCase;

        public MovieViewHolder(MovieListItemBinding movieListItemBinding, ImageMovieUseCase imageMovieUseCase) {
            super(movieListItemBinding.productContainer);
            this.movieListItemBinding = movieListItemBinding;
            this.imageMovieUseCase = imageMovieUseCase;
        }

        void bindMoview(Movie result) {
            if (movieListItemBinding.getViewModel() == null) {
                movieListItemBinding.setViewModel(
                    new MovieListItemViewModel(imageMovieUseCase, result));
            } else {
                movieListItemBinding.getViewModel().setMovie(result);
            }
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressItemBinding progressItemBinding;

        public LoadingViewHolder(ProgressItemBinding progressItemBinding) {
            super(progressItemBinding.container);
        }
    }
}
