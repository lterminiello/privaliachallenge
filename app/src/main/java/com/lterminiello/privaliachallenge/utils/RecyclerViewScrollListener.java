package com.lterminiello.privaliachallenge.utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    private static final String TAG = "RecylcerScrollListener";
    private int mScrollThreshold = 40;
    private int scrolledDistance = 0;
    private static final int HIDE_THRESHOLD = 20;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 2;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private boolean infiniteScrollingEnabled = true;

    private boolean controlsVisible = true;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();

        visibleItemCount = recyclerView.getChildCount();
        if (manager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager)manager;
            firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();
            totalItemCount = gridLayoutManager.getItemCount();
        } else if (manager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager)manager;
            firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
            totalItemCount = linearLayoutManager.getItemCount();
        }


        if (infiniteScrollingEnabled) {
            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                }
            }


            if ((totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold)) {
                onLoadMore();
                loading = true;
            }
        }

        if (firstVisibleItem == 0) {
            if (!controlsVisible) {
                onScrollUp();
                controlsVisible = true;
            }

            return;
        }

        if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
            onScrollDown();
            controlsVisible = false;
            scrolledDistance = 0;
        } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
            onScrollUp();
            controlsVisible = true;
            scrolledDistance = 0;
        }

        if ((controlsVisible && dy>0) || (!controlsVisible && dy <0)) {
            scrolledDistance+=dy;
        }
    }

    public abstract void onScrollUp();
    public abstract void onScrollDown();
    public abstract void onLoadMore();

}
