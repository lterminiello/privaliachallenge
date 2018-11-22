package com.lterminiello.privaliachallenge.usecase.movie;

import com.lterminiello.privaliachallenge.repository.ImageMovieRepository;
import com.lterminiello.privaliachallenge.usecase.AbstractBaseUseCase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ImageMovieUseCase extends AbstractBaseUseCase<String> {

    private ImageMovieRepository imageMovieRepository;
    private String imdbId;

    public ImageMovieUseCase(final ImageMovieRepository imageMovieRepository) {
        this.imageMovieRepository = imageMovieRepository;
    }

    @Override
    public Disposable doExecute() {
        if (useCaseDisposable == null || useCaseDisposable.isDisposed()) {
            getDefaultUseCaseCallback().onStart();
            useCaseDisposable = imageMovieRepository.getPosterMovie(imdbId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    imageUrl -> {
                        getDefaultUseCaseCallback().onSuccess(imageUrl);
                    },
                    throwable -> getDefaultUseCaseCallback().onError(throwable.getMessage())
                );
        }
        return useCaseDisposable;
    }

    public void setImdbId(final String imdbId) {
        this.imdbId = imdbId;
    }
}
