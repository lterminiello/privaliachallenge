package com.lterminiello.privaliachallenge.repository;

import com.lterminiello.privaliachallenge.datasource.ImageMovieDataSource;
import com.lterminiello.privaliachallenge.datasource.LocalImageMovieDataSourceImp;
import com.lterminiello.privaliachallenge.datasource.RemoteImageMovieDataSourceImp;
import com.lterminiello.privaliachallenge.model.MovieImage;
import com.lterminiello.privaliachallenge.room.AppDatabase;
import com.lterminiello.privaliachallenge.utils.StringUtils;
import io.reactivex.Single;

public class ImageMovieRepositoryImp implements ImageMovieRepository {

    private static ImageMovieRepositoryImp instance;

    public static ImageMovieRepositoryImp getInstance() {
        if (instance == null) {
            instance = new ImageMovieRepositoryImp(RemoteImageMovieDataSourceImp.getInstance(),
                LocalImageMovieDataSourceImp.getInstance());
        }

        return instance;
    }

    private ImageMovieDataSource remoteImageMovieDataSource;
    private ImageMovieDataSource localImageMovieDataSource;

    public ImageMovieRepositoryImp(ImageMovieDataSource remoteImageMovieDataSource,
        ImageMovieDataSource localImageMovieDataSource) {
        this.remoteImageMovieDataSource = remoteImageMovieDataSource;
        this.localImageMovieDataSource = localImageMovieDataSource;
    }

    @Override
    public Single<String> getPosterMovie(final String imdbId) {
        return localImageMovieDataSource.getPosterMovie(imdbId).flatMap(url -> {
            if (StringUtils.isBlank(url)) {
                return remoteImageMovieDataSource.getPosterMovie(imdbId).flatMap(s -> {
                    cachePoster(imdbId, s);
                    return Single.just(s);
                });
            }
            return Single.just(url);
        });
    }

    private void cachePoster(String imdbId, String url) {
        AppDatabase.getInstance().movieImageDao().insertAll(new MovieImage(imdbId, url));
    }
}
