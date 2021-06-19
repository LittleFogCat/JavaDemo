package top.littlefogcat.demolist.githubapi.utils;

import okhttp3.*;

import java.util.concurrent.TimeUnit;

public class Http {
    public final static OkHttpClient client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).build();

    public static void get(String url, Callback callback) {
        get(url, null, callback);
    }

    public static void get(String url, Headers headers, Callback callback) {
        get(HttpUrl.get(url), headers, callback);
    }

    public static void get(HttpUrl url, Headers headers, Callback callback) {
        Request.Builder request = new Request.Builder().url(url);
        if (headers != null) request.headers(headers);
        client.newCall(request.build()).enqueue(callback);
    }

    public static HttpUrl.Builder buildUrl(String url) {
        return HttpUrl.get(url).newBuilder();
    }

}
