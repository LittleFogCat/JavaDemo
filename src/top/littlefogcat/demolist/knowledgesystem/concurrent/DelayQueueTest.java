package top.littlefogcat.demolist.knowledgesystem.concurrent;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<MyDelayed> q = new DelayQueue<>();
        q.offer(MyDelayed.get("1", 3000));
        q.offer(MyDelayed.get("2", 6000));
        q.offer(MyDelayed.get("4", 9000));
        q.offer(MyDelayed.get("3", 13000));
        while (q.size() > 0) {
            MyDelayed md = q.take();
            md.run();
        }
    }

    static class MyDelayed implements Delayed, Runnable {
        private String name;
        private long expireTime;

        public static MyDelayed get(String name, long delay) {
            MyDelayed md = new MyDelayed();
            md.name = name;
            md.expireTime = System.currentTimeMillis() + delay;
            return md;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(expireTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) ((getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS)));
        }

        @Override
        public void run() {
            System.out.println(name + " run");
        }
    }
}
