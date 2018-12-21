package soexample.umeng.com.zonghelianxi.utils;

import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import soexample.umeng.com.zonghelianxi.inter.ICallBack;

/**
 * date:2018/12/19
 * author:冯泽林(asus)
 * function:
 */
public class HttpUtils {
    private static volatile HttpUtils Instener;
    private OkHttpClient client;
    private Handler handler = new Handler();

    public HttpUtils() {
        client = new OkHttpClient();
    }

    public static HttpUtils getInstener() {
        if (Instener == null) {
            synchronized (HttpUtils.class) {
                if (Instener == null) {
                    Instener = new HttpUtils();
                }
            }
        }
        return Instener;
    }

    public void get(String url, final ICallBack callBack, final Type type) {
        Request build = new Request.Builder().url(url).get().build();
        Call call = client.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.OnFaidel(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(s, type);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.Onsuccess(o);
                    }
                });
            }
        });
    }
}
