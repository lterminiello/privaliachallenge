package com.lterminiello.privaliachallenge.datasource;

import com.lterminiello.privaliachallenge.model.MovieImage;
import com.lterminiello.privaliachallenge.room.AppDatabase;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class LocalImageMovieDataSourceImp implements ImageMovieDataSource {

    private static LocalImageMovieDataSourceImp instance;

    public static LocalImageMovieDataSourceImp getInstance() {
        if (instance == null) {
            instance =
                new LocalImageMovieDataSourceImp();
        }
        return instance;
    }

    @Override
    public Single<String> getPosterMovie(final String imdbId) {
        return AppDatabase.getInstance().movieImageDao().findByIdImdb(imdbId)
            .switchIfEmpty(Maybe.just(new MovieImage("",""))).flatMapSingle(movieImage -> {
                String url = "";
                if (movieImage != null) {
                    url = movieImage.getMovieImage();
                }
                return Single.just(url);
            });
    }
}
