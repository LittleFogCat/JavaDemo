package top.littlefogcat.demolist.base;

import java.util.concurrent.TimeUnit;

public class ThreadDefaultHandler {
    public static void main(String[] args) {
        while (true) {
            Thread mt = new Thread(() -> {
                System.out.println("mt start");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            mt.start();
            try {
                mt.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("mt end");
        }
    }
}
