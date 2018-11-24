package com.lterminiello.privaliachallenge.usecase.movie;

import com.lterminiello.privaliachallenge.model.Movie;
import com.lterminiello.privaliachallenge.repository.TraktTvRepository;
import com.lterminiello.privaliachallenge.usecase.AbstractPaginateUseCase;

import com.lterminiello.privaliachallenge.utils.Lists;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class TracktTvPopularListUseCase extends AbstractPaginateUseCase<List<Movie>> {

    private TraktTvRepository traktTvRepository;
    private boolean isLastPage = false;

    public TracktTvPopularListUseCase(TraktTvRepository traktTvRepository) {
        this.traktTvRepository = traktTvRepository;
    }

    @Override
    public Disposable doExecute() {
        if (useCaseDisposable == null || useCaseDisposable.isDisposed()) {
            getDefaultUseCaseCallback().onStart();
            useCaseDisposable = traktTvRepository.getPopulars(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    popularMoviesResponse -> {
                        isLastPage = Lists.isNullOrEmpty(popularMoviesResponse);
                        getDefaultUseCaseCallback().onSuccess(popularMoviesResponse);
                        page++;
                    },
                    throwable -> getDefaultUseCaseCallback().onError(throwable.getMessage())
                );
        }
        return useCaseDisposable;
    }

    @Override
    public boolean isLastPage() {
        return isLastPage;
    }

    @Override
    public void resetPage() {
        super.resetPage();
        isLastPage = false;
    }
}
