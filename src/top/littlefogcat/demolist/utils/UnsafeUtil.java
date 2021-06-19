package top.littlefogcat.demolist.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeUtil {
    public static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = Unsafe.class;
        Field field = clazz.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return (Unsafe) field.get(null);
    }
}
