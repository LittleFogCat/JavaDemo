package top.littlefogcat.demolist.knowledgesystem.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static top.littlefogcat.demolist.knowledgesystem.concurrent.MultiThread.sleep;

public class Atomics {

    private final ExecutorService exec = Executors.newCachedThreadPool();
    private final AtomicInteger ai = new AtomicInteger(0);

    private void atomicAdd() {
        for (int j = 0; j < 10; j++) {
            exec.execute(() -> {
                for (int k = 0; k < 10; k++) {
                    int i1 = ai.incrementAndGet(); // ++i
                    System.out.println(i1);
                }
            });
        }
    }

    private int i = 0;

    private void noAtomicAdd() {
        Runnable r = () -> {
            for (int k = 0; k < 1000; k++) {
                System.out.println(++i);
                sleep(1); // 交出CPU控制权，增大竞争
            }
        };
        for (int j = 0; j < 10; j++) {
            exec.execute(r);
        }
    }

    private void endTask() {
        exec.shutdown();
    }

    public static void main(String[] args) {
        Atomics atomic = new Atomics();
//        atomic.atomicAdd();
        atomic.noAtomicAdd();

        atomic.endTask();
    }
}

class Sample {
    private static final ExecutorService exec = Executors.newCachedThreadPool();
//    private static int i = 0;
    private static AtomicInteger i = new AtomicInteger(0);
//    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            for (int k = 0; k < 1000; k++) {
//                i++;
                i.getAndIncrement();
                sleep(1); // 交出CPU控制权，增大竞争
            }
        };
        for (int j = 0; j < 8; j++) {
            Future<?> f = exec.submit(r);
        }
        exec.shutdown();
        exec.awaitTermination(60, TimeUnit.SECONDS); // 等待所有任务执行完毕
        System.out.println(i);
    }
}