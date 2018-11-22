package com.lterminiello.privaliachallenge.usecase;

import com.lterminiello.privaliachallenge.repository.ImageMovieRepositoryImp;
import com.lterminiello.privaliachallenge.repository.TraktTvRepositoryImp;
import com.lterminiello.privaliachallenge.usecase.movie.ImageMovieUseCase;
import com.lterminiello.privaliachallenge.usecase.movie.TracktTvPopularListUseCase;
import com.lterminiello.privaliachallenge.usecase.movie.TracktTvSearchListUseCase;

public class UseCaseFactory {

    public TracktTvPopularListUseCase getTracktTvPopularListUseCase() {
        return new TracktTvPopularListUseCase(TraktTvRepositoryImp.getInstance());
    }

    public TracktTvSearchListUseCase getTracktTvSearchListUseCase() {
        return new TracktTvSearchListUseCase(TraktTvRepositoryImp.getInstance());
    }

    public ImageMovieUseCase getImageMovieUseCase() {
        return new ImageMovieUseCase(ImageMovieRepositoryImp.getInstance());
    }
}
