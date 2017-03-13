package com.alpha58.okhttps;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.alpha58.okhttps.utils.HTTPSUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView mIv_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIv_img = (ImageView) findViewById(R.id.iv_img);
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

    public void getHttpsImg(View view) {
        //自签名https图片链接 （如果链接失效，自行到12306网站找图片）
        String url = "https://travel.12306.cn/imgs/resources/uploadfiles/images/a9b9c76d-36ba-4e4a-8e02-9e6a1a991da0_news_W540_H300.jpg";
        Glide.with(this)
                .load(url)
                .asBitmap()
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        System.out.println("--------------Exception--------------" + e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(mIv_img);
    }
}
