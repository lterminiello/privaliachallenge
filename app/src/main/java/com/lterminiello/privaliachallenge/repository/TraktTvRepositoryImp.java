package com.lterminiello.privaliachallenge.repository;

import com.lterminiello.privaliachallenge.datasource.RemoteTraktTvDataSourceImp;
import com.lterminiello.privaliachallenge.datasource.TraktTvDataSource;
import com.lterminiello.privaliachallenge.model.Movie;
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

    private TraktTvDataSource traktTvDataSource;

    public TraktTvRepositoryImp(TraktTvDataSource remoteTraktTvDataSource) {
        this.traktTvDataSource = remoteTraktTvDataSource;
    }

    @Override
    public Single<List<Movie>> getPopulars(final int page) {
        Single<List<Movie>> resultSingle = traktTvDataSource.getPopulars(page);
        return resultSingle;
    }

    @Override
    public Single<List<Movie>> getMovies(final String query, final int page) {
        Single<List<Movie>> resultSingle = traktTvDataSource.getMovies(query, page);
        return resultSingle;
    }
}
