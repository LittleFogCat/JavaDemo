package top.littlefogcat.demolist.learn.other;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.*;

public class Value {
    public int val;

    Value(int i) {
        val = i;
    }

    public int get() {
        return val;
    }

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException, NoSuchFieldException, IllegalAccessException {
//        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
//        threadLocal.set(1);
//        new Thread(() -> {
//            threadLocal.set(2);
//            System.out.println(threadLocal.get());
//        }).start();
//        Thread.sleep(10);
//        System.out.println(threadLocal.get());
//        Integer i1 = 127;
//        Integer i2 = 127;
//        int i3 = 127;
//        Integer i4 = new Integer(127);
//        Integer i5 = 128;
//        Integer i6 = 128;
//        int i7 = 128;
//        System.out.println(i1 == i2);
//        System.out.println(i1 == i3);
//        System.out.println(i1 == i4);
//        System.out.println(i5 == i6);
//        System.out.println(i5 == i7);
//
//        System.out.println('c' == 'c' + 0);
//        System.out.println(1 == new Integer(1));
//        Map<Integer,Integer> m = new HashMap<>();
//        System.out.println(1 == new Byte((byte) 1));
//        System.out.println(1 == new Double(1));
//        Integer i = m.get("h");
//        System.out.println(1 == i);
//        System.out.println(new ClassLoad().hashCode());
//        System.out.println(+0f == -0F);
////        System.out.println(new ClassLoad().hashCode());
//        String s1 = new String("你好".getBytes("utf8"));
//        String s2 = new String("你好".getBytes("gbk"));
//        System.out.println("s1=" + s1 + ", s2=" + s2);
//        System.out.println(s1.equals(s2));
////        System.out.println(s1.hashCode() == s2.hashCode());
//        Map<String, Integer> map = new HashMap<>();
//        map.put("a", 0);
//        Runnable task = () -> {
//            for (int i = 0; i < 1000; i++) {
//                int a = map.get("a");
//                map.put("a", a + 1);
//            }
//        };
//        Thread t1 = new Thread(task);
//        Thread t2 = new Thread(task);
//        t1.start();
//        t2.start();
//        Thread.sleep(1000);
//        System.out.println(map.get("a"));

        ArrayList<A> l1 = new ArrayList<>();
        ArrayList<A> l2 = new ArrayList<>();
//        System.out.println(l1 + "\n" + l2);
//        l1.add(1);
//
//        Collections.sync

//        for (int i = 0; i < 10; i++) {
//            l1.add(new A());
//        }
//        Object[] elementData = (Object[]) getField(l1, "elementData");
//        System.out.println(elementData.length);

        testArrayList();
        testVector();
    }

    private static void testArrayList() {
        List<Integer> arrList = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            arrList.add(arrList.size());
        }
        System.out.println("arrlist use " + (System.currentTimeMillis() - start));
    }

    private static void testVector() {
        List<Integer> vector = new Vector<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            vector.add(vector.size());
        }
        System.out.println("vector use " + (System.currentTimeMillis() - start));
    }

    private static class A {
    }

    private static Object getField(Object obj, String filedName) throws NoSuchFieldException, IllegalAccessException {
        Class c = obj.getClass();
        Field f = c.getDeclaredField(filedName);
        f.setAccessible(true);
        return f.get(obj);
    }


}
