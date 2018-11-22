package com.lterminiello.privaliachallenge.datasource;

import com.lterminiello.privaliachallenge.model.Movie;
import io.reactivex.Single;
import java.util.List;

public interface TraktTvDataSource {

    Single<List<Movie>> getPopulars(int page);

    Single<List<Movie>> getMovies(String query, int page);

}
