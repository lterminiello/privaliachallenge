package com.lterminiello.privaliachallenge.datasource;

import com.lterminiello.privaliachallenge.model.Movie;
import com.lterminiello.privaliachallenge.model.SearchImageResponse;
import com.lterminiello.privaliachallenge.model.SearchItem;
import com.lterminiello.privaliachallenge.retrofit.RetrofitImageMovieService;
import com.lterminiello.privaliachallenge.retrofit.RetrofitImageMovieServiceFactory;
import com.lterminiello.privaliachallenge.retrofit.RetrofitTraktTvService;
import com.lterminiello.privaliachallenge.retrofit.RetrofitTraktTvServiceFactory;
import com.lterminiello.privaliachallenge.utils.Lists;
import io.reactivex.Single;
import java.util.List;

public class RemoteImageMovieDataSourceImp implements ImageMovieDataSource {

    private static RemoteImageMovieDataSourceImp instance;

    public static RemoteImageMovieDataSourceImp getInstance() {
        if (instance == null) {
            instance =
                new RemoteImageMovieDataSourceImp(RetrofitImageMovieServiceFactory.getInstance().getRetrofitImageMovieService());
        }
        return instance;
    }

    private RetrofitImageMovieService retrofitImageMovieService;

    public RemoteImageMovieDataSourceImp(RetrofitImageMovieService retrofitImageMovieService) {
        this.retrofitImageMovieService = retrofitImageMovieService;
    }

    @Override
    public Single<String> getPosterMovie(final String imdbId) {
        return retrofitImageMovieService.getPosterMovie(imdbId).flatMap(searchImageResponse -> {
            String urlImage = "";
            if (!Lists.isNullOrEmpty(searchImageResponse.getMovieposter())) {
                urlImage = searchImageResponse.getMovieposter().get(0).getUrl();

            }
            return Single.just(urlImage);
        });
    }

}
