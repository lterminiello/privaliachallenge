package com.lterminiello.privaliachallenge.usecase.movie;

import com.lterminiello.privaliachallenge.model.SearchItem;
import com.lterminiello.privaliachallenge.repository.TraktTvRepository;
import com.lterminiello.privaliachallenge.usecase.AbstractPaginateUseCase;
import com.lterminiello.privaliachallenge.utils.Lists;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class TracktTvSearchListUseCase extends AbstractPaginateUseCase<List<SearchItem>> {

    private TraktTvRepository traktTvRepository;
    private boolean isLastPage = false;
    private String query;

    public TracktTvSearchListUseCase(TraktTvRepository traktTvRepository) {
        this.traktTvRepository = traktTvRepository;
    }

    @Override
    public Disposable doExecute() {
        if (useCaseDisposable == null || useCaseDisposable.isDisposed()) {
            getDefaultUseCaseCallback().onStart();
            useCaseDisposable = traktTvRepository.getMovies(query, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    searchMoviesResponse -> {
                        isLastPage = Lists.isNullOrEmpty(searchMoviesResponse);
                        getDefaultUseCaseCallback().onSuccess(searchMoviesResponse);
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

    public void setQuery(final String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
