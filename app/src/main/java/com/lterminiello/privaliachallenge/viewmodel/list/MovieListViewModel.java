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
    private Context context;
    private String query;

    private ObservableBoolean availabilityItems = new ObservableBoolean(false);
    private MutableLiveData<Boolean> loadingPagination = new MutableLiveData<>();
    private ObservableBoolean newSearch = new ObservableBoolean(false);
    private MutableLiveData<List<Movie>> moviesData = new MutableLiveData<>();

    public MovieListViewModel(final TracktTvPopularListUseCase popularListUseCase,
        final TracktTvSearchListUseCase searchListUseCase, final Context context) {

        this.popularListUseCase = popularListUseCase;
        this.searchListUseCase = searchListUseCase;
        this.context = context;
        this.popularListUseCase.setDefaultUseCaseCallback(this);
        this.searchListUseCase.setDefaultUseCaseCallback(this);
        loadingPagination.postValue(false);
    }

    public void getMovies(@Nullable String query) {
        newSearch.set(true);
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
        availabilityItems.set(!Lists.isNullOrEmpty(moviesData.getValue()));
    }

    @Override
    public void onSuccess(final List<Movie> response) {
        loadingPagination.setValue(false);
        newSearch.set(false);
        moviesData.setValue(response);
        availabilityItems.set(!Lists.isNullOrEmpty(moviesData.getValue()));
    }

    @Override
    public void onError(final String message) {
        loadingPagination.setValue(false);
        newSearch.set(false);
    }

    public ObservableBoolean getAvailabilityItems() {
        return availabilityItems;
    }

    public MutableLiveData<Boolean> getLoadingPagination() {
        return loadingPagination;
    }

    public ObservableBoolean getNewSearch() {
        return newSearch;
    }

    public MutableLiveData<List<Movie>> getMoviesData() {
        return moviesData;
    }
}
