package com.lterminiello.privaliachallenge.repository;

import com.lterminiello.privaliachallenge.model.SearchImageResponse;
import io.reactivex.Single;
import retrofit2.http.Path;

public interface ImageMovieRepository {

    Single<String> getPosterMovie(@Path("imdbId") String imdbId);

}
