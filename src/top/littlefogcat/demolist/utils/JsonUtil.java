package top.littlefogcat.demolist.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonUtil {

    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(byte[].class, new TypeAdapter<byte[]>() {
                @Override
                public void write(JsonWriter jsonWriter, byte[] bytes) throws IOException {
                    jsonWriter.jsonValue("\"" + new String(bytes) + "\"");
                }

                @Override
                public byte[] read(JsonReader jsonReader) throws IOException {
                    String s = jsonReader.nextString();
                    return s.getBytes(StandardCharsets.UTF_8);
                }
            })
            .create();

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
