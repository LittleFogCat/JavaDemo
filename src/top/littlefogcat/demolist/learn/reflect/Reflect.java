package top.littlefogcat.demolist.learn.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@SuppressWarnings({"rawtypes", "unchecked", "ResultOfMethodCallIgnored"})
public class Reflect {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException, ClassNotFoundException {
//        String s = new String("hello, world!");
////        System.out.println(s);
////        System.out.println(s.length());
////        modifyString("hello, world!", "you're so cool!");
////        System.out.println(s);
////        System.out.println(s.length());
//////        System.out.println("hello, world!");
//        modifyString(s, "cool");
//        System.out.println(s);
//        System.out.println("hello, world!");
//        String s = "hello, world!";
//        String t = new String("hello, world!");
//        modifyString(s, "you're so cool!");
//        System.out.println("t = " + t);
//        testReflectAnimal();
        loadClass();
    }

    private static void loadClass() throws ClassNotFoundException {
        String className = "top.littlefogcat.demolist.base.jvm.classloader.MyClass";
        Class.forName(className);
//        ClassLoader.getSystemClassLoader().loadClass(className);
    }

    /**
     * 通过反射修改字符串
     */
    private static void modifyString(String src, String dst) throws NoSuchFieldException, IllegalAccessException {
        Field valueField = String.class.getDeclaredField("value");
        valueField.setAccessible(true);
        byte[] oldValue = (byte[]) valueField.get(src);
        byte[] newValue = dst.getBytes();
        System.arraycopy(newValue, 0, oldValue, 0, Math.min(oldValue.length, newValue.length));
        if (newValue.length < oldValue.length) {
            Arrays.fill(oldValue, newValue.length, oldValue.length, (byte) 0);
        }
    }

    private static void testReflectAnimal() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Animal.class;
        Constructor cons = clazz.getDeclaredConstructor();
        cons.setAccessible(true);
        Animal animal = (Animal) cons.newInstance();
        System.out.println(animal);
        Method[] methods = clazz.getDeclaredMethods();
        System.out.println(Arrays.toString(methods));
    }
}
