package top.littlefogcat.demolist.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {
    public static AtomicInteger ai = new AtomicInteger(0);

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {

            }
        }).start();
    }
}
