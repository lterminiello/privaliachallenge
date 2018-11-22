package com.lterminiello.privaliachallenge.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class SearchImageResponse implements Serializable {

    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("movieposter")
    private List<MovieImageData> movieposter;

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(final String imdbId) {
        this.imdbId = imdbId;
    }

    public List<MovieImageData> getMovieposter() {
        return movieposter;
    }

    public void setMovieposter(final List<MovieImageData> movieposter) {
        this.movieposter = movieposter;
    }
}
