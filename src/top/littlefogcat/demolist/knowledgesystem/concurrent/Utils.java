package top.littlefogcat.demolist.knowledgesystem.concurrent;

import java.util.UUID;

public class Utils {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String simulateLongTimeTask(long millis) {
        sleep(millis);
        return UUID.randomUUID().toString();
    }

    public static String simulateLongTimeTask() {
        return simulateLongTimeTask(3000);
    }
}
