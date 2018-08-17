package com.example.administrator.mvpdemo.m.listener;


import com.example.administrator.mvpdemo.m.model.Book;

/**
 * Created by Albert on 2018/08/16.
 */

public interface RxResultListener {
    void onSuccess(Book mBook);
    void onError(String result);
}
