package top.littlefogcat.demolist.test;

import okhttp3.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HTMLText {
    public static final String URL = "https://kns.cnki.net/KXReader/Detail?TIMESTAMP=637454811365771797&DBCODE=CJFD&TABLEName=CJFDLAST2017&FileName=XDBY201713021&RESULT=1&SIGN=xmVtiiQBbmdh1zy2xKL%2bYzIMkEQ%3d";

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(new Request.Builder().url(URL).build()).execute();
        String r = response.body().string();
        File file = new File("C:\\Users\\littlefogcat\\Desktop\\网页" + System.currentTimeMillis() + ".txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
