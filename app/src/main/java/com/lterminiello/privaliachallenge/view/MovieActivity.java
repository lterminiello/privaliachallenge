package com.lterminiello.privaliachallenge.view;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.lterminiello.privaliachallenge.R;
import com.lterminiello.privaliachallenge.usecase.UseCaseFactory;
import com.lterminiello.privaliachallenge.view.list.MovieListFragment;
import com.lterminiello.privaliachallenge.viewmodel.MovieViewModelFactory;

public class MovieActivity extends AppCompatActivity {

    private MovieViewModelFactory movieViewModelFactory;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.movie_activity);

        movieViewModelFactory = new MovieViewModelFactory(getApplicationContext(), new UseCaseFactory());

        if (savedInstanceState == null) {
            MovieListFragment fragment = MovieListFragment.newInstance(movieViewModelFactory);
            getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment, MovieListFragment.TAG).commit();
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
