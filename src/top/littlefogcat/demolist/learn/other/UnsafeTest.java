//package top.littlefogcat.demolist.knowledgesystem.other;
//
//import sun.misc.Unsafe;
//
//import java.lang.reflect.Constructor;
//
//public class UnsafeTest {
//    static {
//        try {
//            Class<Unsafe> unsafeCls = Unsafe.class;
//            Constructor<Unsafe> c = unsafeCls.getDeclaredConstructor();
//            c.setAccessible(true);
//            U = c.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static Unsafe U;
//    private static final int[] arr = new int[10000];
//    private static final int LEN = arr.length;
//    private static final int BASE = U.arrayBaseOffset(int[].class);
//    private static final int SHIFT = 2;
//
//    public static void main(String[] args) {
//        long direct = directAssign();
//        long unsafe = unsafeAssign();
//        System.out.println("direct = " + direct + ", unsafe = " + unsafe);
//    }
//
//    private static long directAssign() {
//        long start = System.nanoTime();
//        for (int i = 0; i < LEN; i++) {
//            arr[i] = i;
//        }
//        return System.nanoTime() - start;
//    }
//
//    private static long unsafeAssign() {
//        long start = System.nanoTime();
//        for (int i = 0; i < LEN; i++) {
//            U.putIntVolatile(arr, BASE + (i << SHIFT), i);
//        }
//        return System.nanoTime() - start;
//    }
//}
