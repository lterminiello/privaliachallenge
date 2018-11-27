package com.lterminiello.privaliachallenge.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.lterminiello.privaliachallenge.model.MovieImage;
import io.reactivex.Maybe;
import java.util.List;

@Dao
public interface MovieImageDao {
    @Query("SELECT * FROM images")
    Maybe<List<MovieImage>> getAll();

    @Query("SELECT * FROM images WHERE id IN (:id)")
    Maybe<List<MovieImage>> loadAllByIds(int[] id);

    @Query("SELECT * FROM images where id_imdb = :idImdb")
    Maybe<MovieImage> findByIdImdb(String idImdb);

    @Insert
    void insertAll(MovieImage... movieImages);

    @Delete
    void delete(MovieImage movieImages);

    @Update
    void updateMovieImage(MovieImage... movieImages);

    @Query("DELETE FROM images")
    public void nukeTable();
}