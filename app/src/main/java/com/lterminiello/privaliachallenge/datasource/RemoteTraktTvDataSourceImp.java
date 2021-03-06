package com.lterminiello.privaliachallenge.datasource;

import com.lterminiello.privaliachallenge.model.Movie;
import com.lterminiello.privaliachallenge.model.SearchItem;
import com.lterminiello.privaliachallenge.retrofit.RetrofitTraktTvService;
import com.lterminiello.privaliachallenge.retrofit.RetrofitTraktTvServiceFactory;
import com.lterminiello.privaliachallenge.utils.Lists;
import io.reactivex.Single;
import java.util.List;

public class RemoteTraktTvDataSourceImp implements TraktTvDataSource {

    private static RemoteTraktTvDataSourceImp instance;

    public static RemoteTraktTvDataSourceImp getInstance() {
        if (instance == null) {
            instance =
                new RemoteTraktTvDataSourceImp(RetrofitTraktTvServiceFactory.getInstance().getRetrofitTraktTvService());
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
    public Single<List<Movie>> getMovies(final String query, final int page) {
        return retrofitTraktTvService.getMoviees(query, page).flatMap(searchItems -> {
            List<Movie> mapperList = Lists.newArrayList();
            for (SearchItem item : searchItems) {
                mapperList.add(item.getMovie());
            }
            return Single.just(mapperList);
        });
    }
}
