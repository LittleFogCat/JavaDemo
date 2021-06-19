package top.littlefogcat.demolist.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Timer {
    public static long timeOf(Runnable r) {
        long start = System.currentTimeMillis();
        r.run();
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static void printExecTime(String name, Runnable r) {
        long time = timeOf(r);
        System.out.println(name + "/ use: " + time + "ms");
    }

    public static void withThread(String name, Runnable r, int threadCount, int loopPerThread) {
        long start = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            service.execute(() -> {
                for (int j = 0; j < loopPerThread; j++) {
                    r.run();
                }
            });
        }
        service.shutdown();
        try {
            service.awaitTermination(1000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(name + "/ use: " + (end - start) + "ms");
    }
}
