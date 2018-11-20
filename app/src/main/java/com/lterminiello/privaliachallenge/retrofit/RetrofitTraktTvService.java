package com.lterminiello.privaliachallenge.retrofit;

import com.lterminiello.privaliachallenge.model.Movie;
import com.lterminiello.privaliachallenge.model.SearchItem;
import io.reactivex.Single;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitTraktTvService {

    @GET("movies/popular")
    Single<List<Movie>> getPopulars(@Query("page") int page);

    @GET("search/movie")
    Single<List<SearchItem>> getMoviees(@Query("query") String query, @Query("page") int page);
}