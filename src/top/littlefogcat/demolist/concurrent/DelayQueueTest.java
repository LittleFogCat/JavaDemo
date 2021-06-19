package top.littlefogcat.demolist.concurrent;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {
    static long startTime = 0;

    public static void main(String[] args) throws InterruptedException {
        startTime = System.currentTimeMillis();

        DelayQueue<Task> queue = new DelayQueue<>();
        queue.offer(new Task("task1", 1000));
        queue.offer(new Task("task2", 4000));
        queue.offer(new Task("task3", 2000));
        queue.offer(new Task("task4", 3000));
        while (queue.size() > 0) {
            Task task = queue.take();
            task.run();
        }
    }


    static class Task implements Delayed {
        String name;
        long t;
        Runnable r = () -> System.out.println(name + " runs at " + (t - startTime));

        Task(String name, long time) {
            this.name = name;
            t = startTime + time;
        }

        public void run() {
            r.run();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = t - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (!(o instanceof Task)) throw new IllegalArgumentException();
            return (int) (t - ((Task) o).t);
        }
    }
}
