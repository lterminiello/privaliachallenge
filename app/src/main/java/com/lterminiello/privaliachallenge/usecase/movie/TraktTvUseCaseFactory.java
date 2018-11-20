package com.lterminiello.privaliachallenge.usecase.movie;

import com.lterminiello.privaliachallenge.repository.TraktTvRepositoryImp;

public class TraktTvUseCaseFactory {

    public TracktTvPopularListUseCase getTracktTvPopularListUseCase(){
        return new TracktTvPopularListUseCase(TraktTvRepositoryImp.getInstance());
    }

    public TracktTvSearchListUseCase getTracktTvSearchListUseCase(){
        return new TracktTvSearchListUseCase(TraktTvRepositoryImp.getInstance());
    }
}
