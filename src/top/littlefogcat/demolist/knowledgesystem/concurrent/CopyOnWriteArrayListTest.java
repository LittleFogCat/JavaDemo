package top.littlefogcat.demolist.knowledgesystem.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

public class CopyOnWriteArrayListTest {
    private static final int LEN = 10_000;
    private static Vector<Integer> v;
    private static CopyOnWriteArrayList<Integer> c;
    private static ExecutorService es = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        useVectorAdd();
        useCListAdd();
        long cTime = timing(CopyOnWriteArrayListTest::useCListGet);
        long vTime = timing(CopyOnWriteArrayListTest::useVectorGet);
        System.out.println("vector: " + vTime + ", cList: " + cTime);
    }

    private static long timing(Runnable r) throws ExecutionException, InterruptedException {
        Future[] futures = new Future[10];
        long start = System.currentTimeMillis();
        for (int i = 0; i < futures.length; i++) {
            futures[i] = es.submit(r);
        }
        for (Future f : futures) {
            f.get();
        }
        return System.currentTimeMillis() - start;
    }

    private static void useVectorGet() {
        for (int i = 0; i < LEN; i++) {
            v.add(i);
            v.get(i / 2);
            v.get(i / 2);
            v.get(i / 2);
            v.get(i / 2);
            v.get(i / 2);
            v.get(i / 2);
            v.get(i / 2);
            v.get(i / 2);
            v.get(i / 2);
            v.get(i / 2);
        }
    }

    private static void useVectorAdd() {
        v = new Vector<>();
        for (int i = 0; i < LEN; i++) {
            v.add(i);
        }
    }

    private static void useCListGet() {
        for (int i = 0; i < LEN; i++) {
            c.add(i);
            c.get(i / 2);
            c.get(i / 2);
            c.get(i / 2);
            c.get(i / 2);
            c.get(i / 2);
            c.get(i / 2);
            c.get(i / 2);
            c.get(i / 2);
            c.get(i / 2);
            c.get(i / 2);
        }
    }

    private static void useCListAdd() {
        c = new CopyOnWriteArrayList<>();
        for (int i = 0; i < LEN; i++) {
            c.add(i);
        }
    }

    public static class WhyArrayListNotThreadSafe {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    a.add(j);
                }
            }).start();
        }
    }
    }
}
