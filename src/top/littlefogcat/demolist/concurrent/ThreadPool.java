package top.littlefogcat.demolist.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(3, 10,
                6000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(2));
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + ": start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": end");
        };
        tpe.execute(r);
        tpe.execute(r);
        tpe.execute(r);
        tpe.execute(r);
        tpe.execute(r);
        tpe.execute(r);
        tpe.execute(r);
    }
}
