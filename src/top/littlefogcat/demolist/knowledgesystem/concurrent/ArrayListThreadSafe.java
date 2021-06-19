package top.littlefogcat.demolist.knowledgesystem.concurrent;

import java.util.ArrayList;

public class ArrayListThreadSafe {

    private static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    list.add(0);
                }
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println(list.size());
    }
}
