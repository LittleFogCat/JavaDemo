package top.littlefogcat.demolist.gson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonTest {
    public static void main(String[] args) {
        String json = "[{\"data\":\"hello\"}]";

        Object o1 = fromJson(json,new TypeToken<List<Entity>>(){}.getType());
        Object o2 = fromJson(json, List.class);

        System.out.println(o1);
        System.out.println(o2);
    }

    static Gson gson = new Gson();

    static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    static <T> T fromJson(String json, Class<T> cls) {
        return gson.fromJson(json, cls);
    }


    public static class Entity {
        public String data;

        @Override
        public String toString() {
            return "Entity{" +
                    "data='" + data + '\'' +
                    '}';
        }
    }
}
