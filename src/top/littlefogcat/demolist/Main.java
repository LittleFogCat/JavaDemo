package top.littlefogcat.demolist;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static AtomicInteger i = new AtomicInteger(0);

    private static Runnable task = new Runnable() {
        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                i.getAndIncrement();
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        Thread.sleep(1000);
        System.out.println(i.get());

        List<String> list = new ArrayList<>();

    }

}
