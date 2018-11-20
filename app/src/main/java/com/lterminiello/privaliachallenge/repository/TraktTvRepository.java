package com.lterminiello.privaliachallenge.repository;

import com.lterminiello.privaliachallenge.model.Movie;
import com.lterminiello.privaliachallenge.model.SearchItem;
import io.reactivex.Single;
import java.util.List;

public interface TraktTvRepository {

    Single<List<Movie>> getPopulars(int page);

    Single<List<SearchItem>> getMovies(String query, int page);

}
