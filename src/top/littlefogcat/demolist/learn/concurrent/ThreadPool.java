package top.littlefogcat.demolist.learn.concurrent;

import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService exec = Executors.newCachedThreadPool();
//
//        Future<?> future = exec.submit(() -> {
//            System.out.println("hello");
//        });
//
//        exec.submit(() -> {
//            System.out.println("world");
//        });
//
//        exec.submit(() -> {
//            System.out.println("hello world");
//            return "hello world";
//        });
        testBlock();
    }

    /**
     * 测试线程池阻塞
     */
    private static void testBlock() {
        ExecutorService exe = Executors.newSingleThreadExecutor();
        exe.execute(() -> System.out.println("hello"));
        exe.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        exe.execute(() -> System.out.println("world"));
    }
}
