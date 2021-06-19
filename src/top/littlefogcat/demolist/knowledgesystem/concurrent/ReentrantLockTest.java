package top.littlefogcat.demolist.knowledgesystem.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {


    private static final ReentrantLock LOCK_RED = new ReentrantLock();
    private static final ReentrantLock LOCK_GREEN = new ReentrantLock();

    public static void main(String[] args) {
        Homework homework = new Homework();
        new Thread(() -> {
            LOCK_RED.lock();
            homework.doChineseByRed();
            LOCK_RED.unlock();
        }).start();
        new Thread(() -> {
            LOCK_GREEN.lock();
            homework.doMathByGreen();
            LOCK_GREEN.unlock();
        }).start();
        homework.doEnglish();
        LOCK_RED.lock();
        LOCK_GREEN.lock();
        homework.play();
        LOCK_GREEN.unlock();
        LOCK_RED.unlock();
    }
}
