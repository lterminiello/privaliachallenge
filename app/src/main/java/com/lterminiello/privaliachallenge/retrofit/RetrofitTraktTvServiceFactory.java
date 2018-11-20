package com.lterminiello.privaliachallenge.retrofit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;


public class RetrofitTraktTvServiceFactory extends AbstractRetrofitServiceFactory {

    private static RetrofitTraktTvServiceFactory instance;

    public static RetrofitTraktTvServiceFactory getInstance(){
        if (instance == null){
            instance = new RetrofitTraktTvServiceFactory();
        }
        return instance;
    }

    public RetrofitTraktTvService getRetrofitProductService() {
        return getRetrofit().create(RetrofitTraktTvService.class);
    }

    @Override
    protected String getBaseUrl() {
        return "https://api.trakt.tv";
    }

    @Override
    protected Interceptor getCustomInterceptor() {
        return chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            //add full info movie
            HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("extended", "full")
                .build();

            // Request customization: add request headers apikey and others
            Request.Builder requestBuilder = original.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("trakt-api-version", "2")
                .addHeader("trakt-api-key", "019a13b1881ae971f91295efc7fdecfa48b32c2a69fe6dd03180ff59289452b8")
                .url(url);

            Request request = requestBuilder.build();

            return chain.proceed(request);
        };
    }
}
