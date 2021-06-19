package top.littlefogcat.demolist.githubapi.utils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;

public abstract class DefaultOkCallback implements Callback {
    public abstract void onSuccess(String response);

    public void onResponse(Call call, Response response) throws IOException {
        onSuccess(response.body().string());
    }

    public void onFailure(Call call, IOException e) {
        e.printStackTrace();
    }
}
