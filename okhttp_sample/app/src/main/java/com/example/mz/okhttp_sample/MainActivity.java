package com.example.mz.okhttp_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/***
 * get多是从服务器请求数据；post则是上传数据到服务器（参数多作为请求体）
 */
public class MainActivity extends AppCompatActivity {
    private TextView mTextView;

    private String mUrl = "http://www.imooc.com";
    OkHttpClient okHttpClient = new OkHttpClient();//全局执行者


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTextView = (TextView) findViewById(R.id.tv_result);
    }

    public void daPost(View view) {
        //1、拿到okhttpClient
        FormEncodingBuilder requestBodyBuilder = new FormEncodingBuilder();//post主要
        //2、构造request
        //2.1、构造requestBody
        RequestBody requestBody = requestBodyBuilder.add("username", "zzy").add("password", "123");

        //创建ruquest
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(mUrl).post(requestBody).build();

        //3、将request封装成call
        Call call = okHttpClient.newCall(request);

        //4、执行call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });


    }


    public void daGet(View view) {

        //1拿到okhttpClient对象
//        OkHttpClient okHttpClient = new OkHttpClient();//全局执行者

        //2.创建Request
        Request.Builder builder = new Request.Builder();
        Request request = builder
                .get()
                .url(mUrl)
                .build();

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

                L.e("onFailure:" + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                L.e("onResponse:");
                final String s = response.body().string();
                L.e(s);

//                InputStream stream = response.body().byteStream(); 为了大文件下载


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(s);
                    }
                });

            }
        });
    }

    //post一个Json字符串到服务器
    public void doPostString(View view) {
//1、
        RequestBody body = RequestBody.create(MediaType.parse("text/plain:chaset=utf-8"), "{username:zzy,password:123}");

//2同上
//        3同上

    }


}
