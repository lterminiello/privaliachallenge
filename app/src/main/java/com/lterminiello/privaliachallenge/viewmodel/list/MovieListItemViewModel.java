package com.lterminiello.privaliachallenge.viewmodel.list;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.lterminiello.privaliachallenge.R;
import com.lterminiello.privaliachallenge.model.Movie;
import com.lterminiello.privaliachallenge.usecase.DefaultUseCaseCallback;
import com.lterminiello.privaliachallenge.usecase.UseCaseUtils;
import com.lterminiello.privaliachallenge.usecase.movie.ImageMovieUseCase;
import com.lterminiello.privaliachallenge.utils.GlideApp;
import com.lterminiello.privaliachallenge.utils.StringUtils;

public class MovieListItemViewModel extends BaseObservable implements DefaultUseCaseCallback<String> {

    private Movie movie;
    private ImageMovieUseCase imageMovieUseCase;
    private String imageUrl;

    public MovieListItemViewModel(ImageMovieUseCase imageMovieUseCase, Movie movie) {
        this.movie = movie;
        this.imageMovieUseCase = imageMovieUseCase;
        imageMovieUseCase.setDefaultUseCaseCallback(this);
        imageMovieUseCase.setImdbId(movie.getIds().get("imdb"));
        UseCaseUtils.execute(imageMovieUseCase);
        imageUrl = "";
        notifyChange();
    }

    public String getTitle() {
        return movie.getTitle() + StringUtils.SPACE + "(" + movie.getYear() + ")";
    }

    public String getDescription() {
        return movie.getOverview();
    }

    @BindingAdapter({ "bind:imageUrl" })
    public static void loadImage(ImageView view, String imageUrl) {
        GlideApp.with(view.getContext()).load(imageUrl).error(R.drawable.ticket).placeholder(R.drawable.ticket).into(view);
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        imageUrl = "";
        imageMovieUseCase.setImdbId(movie.getIds().get("imdb"));
        UseCaseUtils.execute(imageMovieUseCase);
        notifyChange();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Movie getResult() {
        return movie;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onSuccess(final String response) {
        imageUrl = response;
        notifyChange();
    }

    @Override
    public void onError(final String message) {
    }
}
