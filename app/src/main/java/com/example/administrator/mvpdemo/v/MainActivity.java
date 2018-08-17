package com.example.administrator.mvpdemo.v;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mvpdemo.R;
import com.example.administrator.mvpdemo.m.model.Book;
import com.example.administrator.mvpdemo.p.ApiPresenter;
import com.example.administrator.mvpdemo.m.listener.RxResultListener;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private ApiPresenter mPresenter = new ApiPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        mPresenter.onCreate();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getSearchBooks("金瓶梅", null, 0, 1);
            }
        });

        mPresenter.setOnRxResultListener(new RxResultListener() {
            @Override
            public void onSuccess(Book mBook) {
                text.setText(mBook.toString());
            }
            @Override
            public void onError(String result) {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mPresenter.onStop();
    }
}
