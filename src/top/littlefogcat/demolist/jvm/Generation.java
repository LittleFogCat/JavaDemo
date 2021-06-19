package top.littlefogcat.demolist.jvm;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Generation {

    private static final BlockingQueue<String> mQueue = new LinkedBlockingQueue<>(10);

    public static void main(String[] args) {
        runLooper();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                String s0;
                for (; ; ) {
                    String s = UUID.randomUUID().toString();
                    s0 = s + Math.random() * Math.random();
                    System.out.println(s0);
//                mQueue.offer(s);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                }
            }).start();
        }
    }

    private static void runLooper() {
        new Thread(Generation::runLooper0).start();
    }

    private static void runLooper0() {
        for (; ; ) {
            try {
                String msg = mQueue.take();
                System.out.println("looper get a message: " + msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("looper exit");
                return;
            }
        }
    }
}
