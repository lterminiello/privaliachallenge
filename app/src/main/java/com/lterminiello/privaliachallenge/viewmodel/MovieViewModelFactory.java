package com.lterminiello.privaliachallenge.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;
import com.lterminiello.privaliachallenge.usecase.UseCaseFactory;
import com.lterminiello.privaliachallenge.viewmodel.list.MovieListViewModel;

public class MovieViewModelFactory implements ViewModelProvider.Factory {

    private Context context;
    private UseCaseFactory useCaseFactory;

    public MovieViewModelFactory(final Context context, final UseCaseFactory useCaseFactory) {
        this.context = context;
        this.useCaseFactory = useCaseFactory;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieListViewModel.class)) {
            return (T) new MovieListViewModel(useCaseFactory.getTracktTvPopularListUseCase(),
                useCaseFactory.getTracktTvSearchListUseCase(), context);
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}
