package com.lterminiello.privaliachallenge.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Movie implements Serializable {

    @SerializedName("title")
    private String title;
    @SerializedName("year")
    private int year;
    @SerializedName("ids")
    private Map<String, String> ids;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("overview")
    private String overview;
    @SerializedName("released")
    private String released;
    @SerializedName("country")
    private String country;
    @SerializedName("rating")
    private Double rating;
    @SerializedName("genres")
    private List<String> genres;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public Map<String, String> getIds() {
        return ids;
    }

    public void setIds(final Map<String, String> ids) {
        this.ids = ids;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(final String tagline) {
        this.tagline = tagline;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(final String overview) {
        this.overview = overview;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(final String released) {
        this.released = released;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(final double rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(final List<String> genres) {
        this.genres = genres;
    }
}
