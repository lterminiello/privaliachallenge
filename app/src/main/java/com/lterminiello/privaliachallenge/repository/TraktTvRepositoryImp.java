package com.lterminiello.privaliachallenge.repository;

import com.lterminiello.privaliachallenge.datasource.RemoteTraktTvDataSource;
import com.lterminiello.privaliachallenge.datasource.RemoteTraktTvDataSourceImp;
import com.lterminiello.privaliachallenge.model.Movie;
import com.lterminiello.privaliachallenge.model.SearchItem;
import io.reactivex.Single;
import java.util.List;

public class TraktTvRepositoryImp implements TraktTvRepository {

    private static TraktTvRepositoryImp instance;

    public static TraktTvRepositoryImp getInstance() {
        if (instance == null) {
            instance = new TraktTvRepositoryImp(RemoteTraktTvDataSourceImp.getInstance());
        }

        return instance;
    }

    private RemoteTraktTvDataSource remoteTraktTvDataSource;

    public TraktTvRepositoryImp(RemoteTraktTvDataSource remoteTraktTvDataSource) {
        this.remoteTraktTvDataSource = remoteTraktTvDataSource;
    }

    @Override
    public Single<List<Movie>> getPopulars(final int page) {
        Single<List<Movie>> resultSingle = remoteTraktTvDataSource.getPopulars(page);
        return resultSingle;
    }

    @Override
    public Single<List<SearchItem>> getMovies(final String query, final int page) {
        Single<List<SearchItem>> resultSingle = remoteTraktTvDataSource.getMovies(query, page);
        return resultSingle;
    }
}
