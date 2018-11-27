package com.lterminiello.privaliachallenge.viewmodel.list;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableBoolean;
import com.lterminiello.privaliachallenge.model.Movie;
import com.lterminiello.privaliachallenge.usecase.DefaultUseCaseCallback;
import com.lterminiello.privaliachallenge.usecase.UseCaseUtils;
import com.lterminiello.privaliachallenge.usecase.movie.TracktTvPopularListUseCase;
import com.lterminiello.privaliachallenge.usecase.movie.TracktTvSearchListUseCase;
import com.lterminiello.privaliachallenge.utils.Lists;
import com.lterminiello.privaliachallenge.utils.StringUtils;
import java.util.List;
import javax.annotation.Nullable;

public class MovieListViewModel extends ViewModel implements DefaultUseCaseCallback<List<Movie>> {

    private TracktTvPopularListUseCase popularListUseCase;
    private TracktTvSearchListUseCase searchListUseCase;
    private String query;

    private ObservableBoolean availabilityItems = new ObservableBoolean(false);
    private ObservableBoolean loading = new ObservableBoolean(false);
    private ObservableBoolean noResult = new ObservableBoolean(false);

    private MutableLiveData<Boolean> loadingPagination = new MutableLiveData<>();
    private MutableLiveData<Boolean> error = new MutableLiveData<>();
    private MutableLiveData<List<Movie>> moviesData = new MutableLiveData<>();

    public MovieListViewModel(final TracktTvPopularListUseCase popularListUseCase,
        final TracktTvSearchListUseCase searchListUseCase, final Context context) {

        this.popularListUseCase = popularListUseCase;
        this.searchListUseCase = searchListUseCase;
        this.popularListUseCase.setDefaultUseCaseCallback(this);
        this.searchListUseCase.setDefaultUseCaseCallback(this);
        loadingPagination.postValue(false);
    }

    public void getMovies(@Nullable String query) {
        moviesData.setValue(null);
        popularListUseCase.cancel();
        searchListUseCase.cancel();
        searchListUseCase.resetPage();
        popularListUseCase.resetPage();
        this.query = query;
        if (StringUtils.isBlank(query)) {
            UseCaseUtils.execute(popularListUseCase);
        } else {
            searchListUseCase.setQuery(query);
            UseCaseUtils.execute(searchListUseCase);
        }
    }

    public void nextPage() {
        if (StringUtils.isBlank(query)) {
            if (!loadingPagination.getValue() && !popularListUseCase.isLastPage()) {
                UseCaseUtils.execute(popularListUseCase);
                loadingPagination.setValue(true);
            }
        } else {
            if (!loadingPagination.getValue() && !searchListUseCase.isLastPage()) {
                UseCaseUtils.execute(searchListUseCase);
                loadingPagination.setValue(true);
            }
        }
    }

    @Override
    public void onStart() {
        loading.set(Lists.isNullOrEmpty(moviesData.getValue()));
        availabilityItems.set(!Lists.isNullOrEmpty(moviesData.getValue()));
    }

    @Override
    public void onSuccess(final List<Movie> response) {
        loadingPagination.setValue(false);
        loading.set(false);
        if (Lists.isNullOrEmpty(moviesData.getValue()) && Lists.isNullOrEmpty(response)) {
            noResult.set(true);
        } else {
            moviesData.setValue(response);
            availabilityItems.set(true);
            noResult.set(false);
        }

    }

    @Override
    public void onError(final String message) {
        error.setValue(true);
        loading.set(false);
        loadingPagination.setValue(false);
    }

    public ObservableBoolean getAvailabilityItems() {
        return availabilityItems;
    }

    public MutableLiveData<Boolean> getLoadingPagination() {
        return loadingPagination;
    }


    public MutableLiveData<List<Movie>> getMoviesData() {
        return moviesData;
    }

    public MutableLiveData<Boolean> getError() {
        return error;
    }

    public ObservableBoolean getLoading() {
        return loading;
    }

    public ObservableBoolean getNoResult() {
        return noResult;
    }
}
