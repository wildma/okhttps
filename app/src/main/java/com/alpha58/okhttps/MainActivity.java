package com.alpha58.okhttps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getHttpsHtml(View view) {
        Request request = new Request.Builder()
                .url("https://kyfw.12306.cn/otn/")
                .build();
        HTTPSUtils httpsUtils = new HTTPSUtils(this);
        httpsUtils.getInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("--------------onFailure--------------" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("--------------onResponse--------------" + response.body().string());
            }
        });
    }
}
