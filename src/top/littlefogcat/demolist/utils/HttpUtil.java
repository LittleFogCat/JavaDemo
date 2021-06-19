package top.littlefogcat.demolist.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpUtil {
    static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();

    public static String get(String url) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        try {
            Response response = client.newCall(builder.build()).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
