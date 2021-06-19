package top.littlefogcat.demolist.knowledgesystem.concurrent;

public class WaitNotify {

    static final Object lck = new Object();

    private static void testWait() {
        new Thread(() -> {
            synchronized (lck) {
                System.out.println("thread 1");
                try {
                    lck.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 1 end");
            }
        }).start();
        new Thread(() -> {
            synchronized (lck) {
                System.out.println("thread 2");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("notify");
                lck.notify();
                System.out.println("thread 2 end");
            }
        }).start();
    }

    public static void main(String[] args) {
        testWait();
    }

}
