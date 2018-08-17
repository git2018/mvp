package com.example.administrator.mvpdemo.m.api;

import com.example.administrator.mvpdemo.m.model.Book;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Albert on 2018/08/16.
 */

public interface ApiList {

    @GET("book/search")
    Observable<Book> getSearchBooks(@Query("q") String name,
                                    @Query("tag") String tag,
                                    @Query("start") int start,
                                    @Query("count") int count);
}
