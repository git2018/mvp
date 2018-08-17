package com.example.administrator.mvpdemo.m.api;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Albert on 2018/08/16.
 */

public class RetrofitHelper {

    private static RetrofitHelper instance = null;

    private Retrofit mRetrofit = null;

    public static synchronized RetrofitHelper getInstance(){
        if (instance == null){
            instance = new RetrofitHelper();
        }
        return instance;
    }

    private RetrofitHelper(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        }

    public ApiList getApi(){
        return mRetrofit.create(ApiList.class);
    }
}
