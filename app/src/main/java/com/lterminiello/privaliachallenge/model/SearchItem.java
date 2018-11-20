package com.lterminiello.privaliachallenge.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class SearchItem implements Serializable {

    @SerializedName("type")
    private String type;
    @SerializedName("score")
    private Double score;
    @SerializedName("movie")
    private Movie movie;

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public double getScore() {
        return score;
    }

    public void setScore(final double score) {
        this.score = score;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(final Movie movie) {
        this.movie = movie;
    }
}
