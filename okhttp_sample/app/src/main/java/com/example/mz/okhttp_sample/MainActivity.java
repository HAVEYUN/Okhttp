package com.example.mz.okhttp_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        jjf？
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doGet(View view) {

        //1拿到okhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();//全局执行者

        //2.创建Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url("http://www.imooc.com/").build();

        //3.将Request封装成call
        Call call = okHttpClient.newCall(request);

        //4.执行call

//        try {
//            Response response = call.execute();//立即执行
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //4.执行call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }


}
