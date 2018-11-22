package com.lterminiello.privaliachallenge.retrofit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

public class RetrofitImageMovieServiceFactory extends AbstractRetrofitServiceFactory {

    private static RetrofitImageMovieServiceFactory instance;

    public static RetrofitImageMovieServiceFactory getInstance(){
        if (instance == null){
            instance = new RetrofitImageMovieServiceFactory();
        }
        return instance;
    }

    public RetrofitImageMovieService getRetrofitImageMovieService() {
        return getRetrofit().create(RetrofitImageMovieService.class);
    }

    @Override
    protected String getBaseUrl() {
        return "http://webservice.fanart.tv";
    }

    @Override
    protected Interceptor getCustomInterceptor() {
        return chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            //add full info movie
            HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", "dfb9e828d65f5b7fc6517aebdf7c9399")
                .build();

            // Request customization: add request headers apikey and others
            Request.Builder requestBuilder = original.newBuilder()
                .url(url);

            Request request = requestBuilder.build();

            return chain.proceed(request);
        };
    }
}
