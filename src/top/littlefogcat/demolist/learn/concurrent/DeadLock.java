package top.littlefogcat.demolist.learn.concurrent;

public class DeadLock {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
//        new /* thread 1 */ Thread(() -> {
//            synchronized (lock1) {
//                sleep(100);
//                try {
//                    lock1.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronized (lock2) {
//                    System.out.println(1);
//                    lock2.notify();
//                }
//            }
//            System.out.println("thread 1 exit");
//        }).start();
//        new /* thread 2 */ Thread(() -> {
//            synchronized (lock1) {
//                sleep(100);
//                try {
//                    lock1.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronized (lock2) {
//                    System.out.println(3);
//                    lock2.notify();
//                }
//            }
//            System.out.println("thread 2 exit");
//        }).start();
//        new /* thread 3 */ Thread(() -> {
//            synchronized (lock2) {
//                sleep(100);
//                synchronized (lock1) {
//                    System.out.println(2);
//                    lock1.notifyAll();
//                }
//            }
//            System.out.println("thread 3 exit");
//        }).start();
//
//        Thread.sleep(1000);
        deadLockSample();
    }

    static void deadLockSample() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                synchronized (lock1) {
                    System.out.println("thread 1 acquires lock1");
                    Thread.sleep(100);
                    try {
                        lock1.wait(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println("thread 1 acquires lock2");
                        lock2.notify();
                    }
                    System.out.println("thread 1 release lock2");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                synchronized (lock2) {
                    Thread.sleep(100);
                    System.out.println("thread 2 acquires lock2");
                    try {
                        lock2.wait(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1) {
                        System.out.println("thread 2 acquires lock1");
                        lock1.notify();
                    }
                    System.out.println("thread 2 release lock2");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
