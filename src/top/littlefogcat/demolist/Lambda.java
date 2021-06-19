package top.littlefogcat.demolist;

import java.util.concurrent.Callable;

public class Lambda {

    public static interface FakeRunnable{
        void run();
    }

    public String submit(Runnable r) {
        return "";
    }

    public <T> T submit(Callable<T> c) throws Exception {
        return c.call();
    }

    public static String t() {
        return "";
    }

    public static void funVoid(){}

    public static void main(String[] args) throws Exception {
        Lambda lambda = new Lambda();
        lambda.submit(Lambda::t);
        lambda.submit(Lambda::funVoid);
    }

}
