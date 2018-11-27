package com.lterminiello.privaliachallenge;

import android.app.Application;
import android.content.Context;
import com.facebook.stetho.Stetho;
import com.lterminiello.privaliachallenge.room.AppDatabase;
import com.lterminiello.privaliachallenge.usecase.UseCaseFactory;
import com.lterminiello.privaliachallenge.viewmodel.MovieViewModelFactory;

public class TrackTvApplication extends Application {


    private static MovieViewModelFactory movieViewModelFactory;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Stetho.initializeWithDefaults(this);
        AppDatabase.initDatabaseRoom(getApplicationContext());
    }

    public static MovieViewModelFactory getMovieViewModelFactory() {
        if (movieViewModelFactory == null) {
            movieViewModelFactory = new MovieViewModelFactory(getAppContext(), new UseCaseFactory());
        }
        return movieViewModelFactory;
    }

    public static Context getAppContext() {
        return context;
    }
}
