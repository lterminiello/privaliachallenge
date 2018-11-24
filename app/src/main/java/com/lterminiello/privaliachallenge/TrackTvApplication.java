package com.lterminiello.privaliachallenge;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.lterminiello.privaliachallenge.room.AppDatabase;

public class TrackTvApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        AppDatabase.initDatabaseRoom(getApplicationContext());
    }
}
