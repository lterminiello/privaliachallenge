package com.lterminiello.privaliachallenge.usecase;

import io.reactivex.disposables.CompositeDisposable;

public class UseCaseUtils {

    private static CompositeDisposable compositeDisposable = new CompositeDisposable();

    public static void execute(AbstractBaseUseCase abstractBaseUseCase){
        compositeDisposable.add(abstractBaseUseCase.doExecute());
    }

}
