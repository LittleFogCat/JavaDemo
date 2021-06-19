package top.littlefogcat.demolist.githubapi;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import top.littlefogcat.demolist.githubapi.utils.DefaultOkCallback;
import top.littlefogcat.demolist.githubapi.utils.Http;

import java.io.IOException;
import java.util.Base64;

public class GithubApi {
    public static final String CLIENT_ID = "db4c7c4dcfc32b1af47f";
    public static final String CLIENT_SECRET = "dc6f66d41af4a6b48e3de1aaa8d90dd9909574b3";

    private static final String TOKEN = "d5dc16ede3758eb7ffcfe4f87bfe92fd4b7f4420";

    public static void main(String[] args) throws IOException {
//        HttpUrl url = Http.buildUrl("https://api.github.com/user?username=littlefogcat")
//                .username("littlefogcat")
//                .password("githubjjj888")
//                .build();
//        System.out.println("real url: " + url);
//        Headers headers = new Headers.Builder()
//                .add("Accept", "application/vnd.github.v3+json")
//                .build();
//        Http.get(url, headers, new DefaultOkCallback() {
//            @Override
//            public void onSuccess(String response) {
//                System.out.println(response);
//            }
//        });

        Request.Builder request = new Request.Builder();
        request.url("https://api.github.com/user");
        String auth = "Basic " + new String(Base64.getEncoder().encode(("littlefogcat:" + TOKEN).getBytes()));
        System.out.println(auth);
        request.addHeader("Authorization", auth);

        Response response = Http.client.newCall(request.build()).execute();
        System.out.println(response.body().string());

        Headers responseHeaders = response.headers();
        System.out.println(responseHeaders);
    }
}
