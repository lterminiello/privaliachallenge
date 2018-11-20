package com.lterminiello.privaliachallenge.datasource;

import com.lterminiello.privaliachallenge.model.Movie;
import com.lterminiello.privaliachallenge.model.SearchItem;
import com.lterminiello.privaliachallenge.retrofit.RetrofitTraktTvService;
import com.lterminiello.privaliachallenge.retrofit.RetrofitTraktTvServiceFactory;
import io.reactivex.Single;
import java.util.List;

public class RemoteTraktTvDataSourceImp implements RemoteTraktTvDataSource {

    private static RemoteTraktTvDataSourceImp instance;

    public static RemoteTraktTvDataSourceImp getInstance() {
        if (instance == null) {
            instance =
                new RemoteTraktTvDataSourceImp(RetrofitTraktTvServiceFactory.getInstance().getRetrofitProductService());
        }
        return instance;
    }

    private RetrofitTraktTvService retrofitTraktTvService;

    public RemoteTraktTvDataSourceImp(RetrofitTraktTvService retrofitTraktTvService) {
        this.retrofitTraktTvService = retrofitTraktTvService;
    }

    @Override
    public Single<List<Movie>> getPopulars(final int page) {
        Single<List<Movie>> observable = retrofitTraktTvService.getPopulars(page);
        return observable;
    }

    @Override
    public Single<List<SearchItem>> getMovies(final String query, final int page) {
        Single<List<SearchItem>> observable = retrofitTraktTvService.getMoviees(query, page);
        return observable;
    }
}
