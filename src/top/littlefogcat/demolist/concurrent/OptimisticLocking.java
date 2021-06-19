package top.littlefogcat.demolist.concurrent;


import sun.misc.Unsafe;
import top.littlefogcat.demolist.utils.Timer;
import top.littlefogcat.demolist.utils.UnsafeUtil;

import java.util.concurrent.TimeUnit;

public class OptimisticLocking {
    public volatile int i = 0;
    public static final Object LOCK = new Object();
    static Unsafe unsafe;
    public static final long I_OFFSET;

    static {
        try {
            Class<?> clazz = OptimisticLocking.class;
            unsafe = UnsafeUtil.getUnsafe();
            I_OFFSET = unsafe.objectFieldOffset(clazz.getDeclaredField("i"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error(e);
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        OptimisticLocking obj = new OptimisticLocking();
        System.out.println(obj.i);
        Timer.withThread("乐观锁", obj::useOptimisticLock, 100, 1000);
        System.out.println(obj.i);
        obj.i = 0;
        Timer.withThread("悲观锁", obj::usePessimisticLock, 100, 1000);
        System.out.println(obj.i);
    }

    // 乐观锁
    public void useOptimisticLock() {
        while (true) {
            final int oldI = i;
            handleSomething(oldI); // handle something
            if (unsafe.compareAndSwapInt(this, I_OFFSET, oldI, oldI + 1)) {
                return;
            }
        }
    }

    // 悲观锁
    public void usePessimisticLock() {
        synchronized (LOCK) {
            handleSomething(i); // handle something
            i++;
        }
    }

    private void handleSomething(int i) {
        try {
            // handle something
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
