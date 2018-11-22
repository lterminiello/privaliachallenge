package com.lterminiello.privaliachallenge.usecase;

public abstract class AbstractPaginateUseCase<T> extends AbstractBaseUseCase<T> {

    protected int totalItemArchived = 0;
    protected int totalItems;
    protected int page = 1;

    public boolean isLastPage() {
        return totalItems != 0 && totalItemArchived >= totalItems;
    }

    protected void calculatePagination(int totalNewItem, int totalItems) {
        this.totalItemArchived += totalNewItem;
        this.totalItems = totalItems;
    }

    public void resetPage(){
        totalItemArchived = 0;
        page = 1;
    }

    protected int getCurrentOffset(){
        return totalItemArchived;
    }

}
