package top.littlefogcat.demolist.jvm;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ActivityThread extends Thread {
    private static final BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    private static class Message {
        int what;
        Object obj;

        @Override
        public String toString() {
            return "Massage{" +
                    "what=" + what +
                    ", obj=" + obj +
                    '}';
        }
    }

    @Override
    public void run() {
        // 从message queue中读取消息
        for (; ; ) {
            try {
                Message msg = queue.take();
                System.out.println("received message: " + msg.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private static void simulateSendMessage() {
        new Thread(() -> {
            for (; ; ) {
                Message m = new Message();
                m.what = new Random().nextInt(100);
                m.obj = UUID.randomUUID().toString();
                queue.offer(m);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        ActivityThread at = new ActivityThread();
        at.start();
        simulateSendMessage();
    }
}
