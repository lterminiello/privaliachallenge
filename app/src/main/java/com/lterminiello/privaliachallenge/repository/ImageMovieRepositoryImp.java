package com.lterminiello.privaliachallenge.repository;

import com.lterminiello.privaliachallenge.datasource.ImageMovieDataSource;
import com.lterminiello.privaliachallenge.datasource.RemoteImageMovieDataSourceImp;
import com.lterminiello.privaliachallenge.datasource.RemoteTraktTvDataSourceImp;
import com.lterminiello.privaliachallenge.datasource.TraktTvDataSource;
import com.lterminiello.privaliachallenge.model.Movie;
import io.reactivex.Single;
import java.util.List;

public class ImageMovieRepositoryImp implements ImageMovieRepository {

    private static ImageMovieRepositoryImp instance;

    public static ImageMovieRepositoryImp getInstance() {
        if (instance == null) {
            instance = new ImageMovieRepositoryImp(RemoteImageMovieDataSourceImp.getInstance());
        }

        return instance;
    }

    private ImageMovieDataSource imageMovieDataSource;

    public ImageMovieRepositoryImp(ImageMovieDataSource imageMovieDataSource) {
        this.imageMovieDataSource = imageMovieDataSource;
    }

    @Override
    public Single<String> getPosterMovie(final String imdbId) {
        return null;
    }
}
