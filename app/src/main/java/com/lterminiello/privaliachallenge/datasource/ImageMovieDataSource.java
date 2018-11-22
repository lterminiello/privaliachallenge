package com.lterminiello.privaliachallenge.datasource;

import com.lterminiello.privaliachallenge.model.SearchImageResponse;
import io.reactivex.Single;
import retrofit2.http.Path;

public interface ImageMovieDataSource {

    Single<String> getPosterMovie(@Path("imdbId") String imdbId);

}
