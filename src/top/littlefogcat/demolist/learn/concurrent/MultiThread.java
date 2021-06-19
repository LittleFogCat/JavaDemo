package top.littlefogcat.demolist.learn.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThread {

    public static void future() throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<?> f = exec.submit(() -> {
            Thread.sleep(3000);
            return "hello future";
        });
        exec.shutdown();
        System.out.print("future: ");
        System.out.println(f.get());
    }

    @SuppressWarnings("Convert2MethodRef")
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        future();

        ExecutorService exec = Executors.newCachedThreadPool();

        // execute
//        exec.execute(() -> {
//            try {
//                Thread.sleep(1000); // 模拟耗时操作
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("execute");
//        });

        // submit
        Future<?> f1 = exec.submit(() -> {
            doTask1(); // sleep 3 sec 模拟耗时操作
        });
        Future<?> f2 = exec.submit(() -> {
            doTask2(); // sleep 2 sec
        });
        doTask3(); // sleep 1 sec
        f1.get();
        f2.get();
        doTask4();
        exec.shutdown();

        Future<String> future = exec.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Utils.simulateLongTimeTask();
            }
        });
        String response = future.get();
        System.out.println(response);
    }


    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void doTask1() {
        sleep(3000); // 模拟耗时操作task1
        System.out.println("task1");
    }

    private static void doTask2() {
        sleep(3000); // 模拟耗时操作task1
        System.out.println("task1");
    }

    private static void doTask3() {
        sleep(3000); // 模拟耗时操作task1
        System.out.println("task1");
    }

    private static void doTask4() {
        sleep(3000); // 模拟耗时操作task1
        System.out.println("task1");
    }

    public static class HomeworkSimulator extends Homework {

        public void simulate() throws ExecutionException, InterruptedException {
            ExecutorService exec = Executors.newCachedThreadPool();
            Future<?> chineseHomework = exec.submit(this::doChineseByRed); // 小红帮小明做语文
            Future<?> mathHomework = exec.submit(this::doMathByGreen); // 小绿帮小明做数学
            doEnglish(); // 小明自己做英语作业
            chineseHomework.get(); // 等待语文作业
            mathHomework.get(); // 等待数学作业
            play();
        }

        public void simulateByAtomicInt() {
            AtomicInteger i = new AtomicInteger(0);
            new Thread(() -> {
                doChineseByRed();
                i.getAndIncrement();
            }).start();
            new Thread(() -> {
                doMathByGreen();
                i.getAndIncrement();
            }).start();
            doEnglish();
            while (i.get() > 0) {
                sleep(10);
            }
            play();
        }

        public void simulateByLock() {
            ReentrantLock lockRed = new ReentrantLock();
            ReentrantLock lockGreen = new ReentrantLock();
            new Thread(() -> {
                lockRed.lock();
                doChineseByRed();
                lockRed.unlock();
            }).start();
            new Thread(() -> {
                lockGreen.lock();
                doMathByGreen();
                lockGreen.unlock();
            }).start();
            doEnglish();
            sleep(10); // 为了让线程先拿到锁
            lockRed.lock();
            lockGreen.lock();
            play();
            lockGreen.unlock();
            lockRed.unlock();
        }

        public void simulateByCallable() throws ExecutionException, InterruptedException {
            ExecutorService exec = Executors.newCachedThreadPool();
            Future<Homework> chineseHomework = exec.submit(this::doChineseByRed); // 小红帮小明做语文
            Future<Homework> mathHomework = exec.submit(this::doMathByGreen); // 小绿帮小明做数学
            Homework english = doEnglish(); // 小明自己做英语作业
            Homework chinese = chineseHomework.get(); // 等待语文作业
            Homework math = mathHomework.get(); // 等待数学作业
            Homework.handIn(chinese, math, english); // 交作业
            play();
        }

        public static void main(String[] args) throws ExecutionException, InterruptedException {
            HomeworkSimulator hs = new HomeworkSimulator();
//            hs.simulate();
//            hs.simulateByThread();
//            hs.simulateByLock();
            hs.simulateByCallable();
        }

    }
}
