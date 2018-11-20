package com.lterminiello.privaliachallenge.usecase;

import io.reactivex.disposables.Disposable;

public abstract class AbstractBaseUseCase<T> {

    private DefaultUseCaseCallback<T> defaultUseCaseCallback;
    protected Disposable useCaseDisposable;

    public abstract Disposable doExecute();

    public void setDefaultUseCaseCallback(DefaultUseCaseCallback<T> defaultUseCaseCallback) {
        this.defaultUseCaseCallback = defaultUseCaseCallback;
    }

    protected DefaultUseCaseCallback<T> getDefaultUseCaseCallback() {
        return defaultUseCaseCallback;
    }


    public void cancel() {
        if (useCaseDisposable != null && !useCaseDisposable.isDisposed()) {
            useCaseDisposable.dispose();
        }
    }
}
