package com.lterminiello.privaliachallenge.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.lterminiello.privaliachallenge.dao.MovieImageDao;
import com.lterminiello.privaliachallenge.model.MovieImage;

@Database(entities = {MovieImage.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "database_movies";

    private static AppDatabase APP_DATABASE;

    public static AppDatabase getInstance() {
        if (APP_DATABASE == null) {
            throw new ExceptionInInitializerError("The DB not init");
        }
        return APP_DATABASE;
    }

    public abstract MovieImageDao movieImageDao();

    public static void initDatabaseRoom(Context applicationContext) {
        if (APP_DATABASE == null) {
            APP_DATABASE = Room
                .databaseBuilder(applicationContext, AppDatabase.class, DB_NAME).build();
        }
    }
}