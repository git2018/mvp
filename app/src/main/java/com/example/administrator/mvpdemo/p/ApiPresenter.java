package com.example.administrator.mvpdemo.p;

import com.example.administrator.mvpdemo.m.model.Book;
import com.example.administrator.mvpdemo.m.api.RetrofitHelper;
import com.example.administrator.mvpdemo.m.listener.RxResultListener;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Albert on 2018/08/16.
 */

public class ApiPresenter {
    private CompositeSubscription mCompositeSubscription;
    private RxResultListener rxResultListener;
    private Book mBook;

    public void onCreate() {
        mCompositeSubscription = new CompositeSubscription();
    }


    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    public void setOnRxResultListener(RxResultListener listener) {
        rxResultListener = listener;
    }

    public void getSearchBooks(String name,String tag,int start,int count){
        mCompositeSubscription.add(RetrofitHelper.getInstance().getApi().getSearchBooks(name,tag,start,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onCompleted() {
                        if (mBook != null){
                            rxResultListener.onSuccess(mBook);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        rxResultListener.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(Book book) {
                        mBook = book;
                    }
                })
        );
    }
}
