package com.lterminiello.privaliachallenge.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "images")
public class MovieImage {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "id_imdb")
    private String idImdb;

    @ColumnInfo(name = "movie_image")
    private String movieImage;

    public MovieImage(final String idImdb, final String movieImage) {
        this.idImdb = idImdb;
        this.movieImage = movieImage;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getIdImdb() {
        return idImdb;
    }

    public void setIdImdb(final String idImdb) {
        this.idImdb = idImdb;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(final String movieImage) {
        this.movieImage = movieImage;
    }
}
