package top.littlefogcat.demolist.concurrent;

import java.util.concurrent.TimeUnit;

public class Volatile {

    class A {
        volatile int val = 0;
    }

    private A a = new A();

    public static void main(String[] args) {
        Volatile v = new Volatile();
        new Thread(() -> {
            while (true) {
                if (v.a.val == 5) break;
            }
            System.out.println(v.a.val);
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                v.a.val++;
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 exits");
        }).start();


    }
}
