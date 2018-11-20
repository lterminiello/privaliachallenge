package com.lterminiello.privaliachallenge.utils;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;
import com.lterminiello.privaliachallenge.R;

public class SnackbarUtils {

    public static Snackbar makeSnackbarError(View container, @StringRes int text) {
        return Snackbar
            .make(container, container.getContext().getResources().getString(text), Snackbar.LENGTH_INDEFINITE)
            .setAction("CLOSE", view -> {

            })
            .setActionTextColor(container.getContext().getResources().getColor(R.color.colorAccent));
    }
}