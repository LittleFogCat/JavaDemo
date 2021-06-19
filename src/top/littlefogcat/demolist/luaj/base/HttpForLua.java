package top.littlefogcat.demolist.luaj.base;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpForLua {
    public static OkHttpClient client = new OkHttpClient();

    public static String get(String url) {
        try {
            Response response = client.newCall(new Request.Builder().url(url).build()).execute();
            if (response.body() != null) {
                return response.body().string();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
