package zzy.okhttp0309;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void DaGet(View view) {
        //全局执行者:拿到okhttpClient
        OkHttpClient okHttpClient = new OkHttpClient();

        //创建Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url("http://read.qidian.com/chapter/f-sMVn2S8Zs1/a8oSCOzIq9I1").build();

        //将request封装成call
        Call call = okHttpClient.newCall(request);


        //执行call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.e("onFailure: " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                L.e("onResponse");
                String string = response.body().string();
                L.e(string);
            }
        });


    }
}
