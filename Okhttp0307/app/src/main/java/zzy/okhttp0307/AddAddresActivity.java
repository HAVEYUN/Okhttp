package zzy.okhttp0307;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Author: 张智远  PC:MZ
 * Time: 2017/3/7 15:22
 * Email: 826680069@qq.com
 * Instruction: please enter class instruction here
 */
public class AddAddresActivity extends AppCompatActivity {
    String url = "http://www.csdn.net/";

    @Override

    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Get();
        Post();//Callback,StringCallback,BitmapCallback

        PostJSON();

        PostFile();
        PostSingleTable();
    }

    public void Get() {

        OkHttpUtils
                .get()
                .url(url)
                .addParams("username", "hyman")
                .addParams("password", "123")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });


    }

    public void Post() {
        OkHttpUtils
                .post()
                .url(url)
                .addParams("username", "hyman")
                .addParams("password", "123")
                .build()


//                2-------------------------StringCallback--------------------

//        .execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//
//            }
//        });

//            3------------------显示图片------------------

                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Bitmap response, int id) {

                    }
                });

    }

    /**
     * 提交一个Gson字符串到服务器端，注意：传递JSON的时候，不要通过addHeader去设置contentType，
     * 而使用.mediaType(MediaType.parse("application/json; charset=utf-8"))
     */
    public void PostJSON() {


        OkHttpUtils.postString()
                .url(url)
                .content(new Gson().toJson(new User("zhy", "123")))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new MyStringCallback());
    }

    /**
     * 将文件作为请求体，发送到服务器。
     */
    public void PostFile() {
        OkHttpUtils
                .postFile()
                .url(url)
                .file("")
                .build()
                .execute(new MyStringCallback());

    }

    /**
     * Post表单形式上传文件
     */
    public void PostSingleTable() {
        OkHttpUtils.post()//
                // 支持单个多个文件，addFile的第一个参数为文件的key，即类别表单中<input type="file" name="mFile"/>的name属性。
                .addFile("mFile", "messenger_01.png", file)//
                .addFile("mFile", "test1.txt", file2)//
                .url(url)
                .params(params)//
                .headers(headers)//
                .build()//
                .execute(new MyStringCallback());

    }
//--------------------------------华丽分割线------------------------------------
    /**
     * 自定义CallBack
     * 例如希望回调User对象：
     */
    public abstract class UserCallback extends Callback<User> {
        @Override
        public User parseNetworkResponse(Response response) throws IOException {
            String string = response.body().string();
            User user = new Gson().fromJson(string, User.class);
            return user;
        }
    }
    /**
     *
        OkHttpUtils
     .get()//
     .url(url)//
     .addParams("username", "hyman")//
     .addParams("password", "123")//
     .build()//
     .execute(new UserCallback()
     {
     @Override public void onError(Request request, Exception e)
     {
     mTv.setText("onError:" + e.getMessage());
     }

     @Override public void onResponse(User response)
     {
     mTv.setText("onResponse:" + response.username);
     }
     });
     */

    //



}
