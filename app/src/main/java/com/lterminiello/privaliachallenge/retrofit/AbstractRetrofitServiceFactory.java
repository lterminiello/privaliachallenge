package com.lterminiello.privaliachallenge.retrofit;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class AbstractRetrofitServiceFactory {

    private Retrofit retrofit;

    //FIXME agregar   if (BuildConfig.DEBUG)  para StethoInterceptor
    public AbstractRetrofitServiceFactory() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .addInterceptor(getCustomInterceptor())
            .build();

        retrofit = new Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build();
    }

    protected Retrofit getRetrofit() {
        return retrofit;
    }

    protected abstract String getBaseUrl();

    protected abstract Interceptor getCustomInterceptor();
}
