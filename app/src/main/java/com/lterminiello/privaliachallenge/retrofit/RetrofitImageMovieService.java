package com.lterminiello.privaliachallenge.retrofit;

import com.lterminiello.privaliachallenge.model.SearchImageResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitImageMovieService {

    @GET("v3/movies/{imdbId}")
    Single<SearchImageResponse> getPosterMovie(@Path("imdbId") String imdbId);
}
