package top.littlefogcat.demolist.knowledgesystem.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Volatile {

    private static boolean b = true;
    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
//        Runnable r = () -> {
//            for (int j = 0; j < 1000; j++) {
//                i++;
//            }
//        };
//        Thread t1 = new Thread(r);
//        Thread t2 = new Thread(r);
//        t1.start();
//        t2.start();
//        Thread.sleep(2000);
//        System.out.println(i);
//        Runnable task = () -> {
//            i = selfAdd(i);
//            System.out.println(i);
//        };
//        for (int j = 0; j < 100; j++) {
//            Thread.sleep(5);
//            System.out.println();
//            i = 0;
//            new Thread(() -> {
//                Math.toIntExact(i);
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                if (i == 0) {
//                    System.out.print("1");
//                    return;
//                }
//                System.out.print("3");
//            }).start();
//            new Thread(() -> {
//                i++;
//                System.out.print("2");
//            }).start();
//        }

        new Thread(() -> {
            while (b) System.out.println();
            ;
            b = true;
            System.out.println("thread 1 get out");
        }).start();
        Thread.sleep(10);
        new Thread(() -> {
            b = false;
            System.out.println("thread 2 get out");
        }).start();
    }
//
//    private static int selfAdd(int val) {
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return val + 1;
//    }

    private static class Sample1 {
        private static volatile boolean flag = true;

        public static void main(String[] args) {
            Thread t1 = new Thread(() -> {
                while (flag) ;
                System.out.println("thread 1 out");
            });
            Thread t2 = new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = false;
                System.out.println("thread 2 out");
            });
            Thread t3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(flag);
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(flag);
                }
            });
            t1.start();
            t2.start();
            t3.start();
        }
    }

    private static class Sample2 {
        private volatile static int i = 0;

        public static void main(String[] args) throws InterruptedException {
            Runnable task = () -> {
                for (int j = 0; j < 1000; j++) {
                    i++;
                    Thread.yield();
                }
            };
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.submit(task);
            exec.submit(task);
            exec.shutdown();
            exec.awaitTermination(60, TimeUnit.SECONDS); // 等待任务执行完毕
            System.out.println(i);
        }
    }
}
