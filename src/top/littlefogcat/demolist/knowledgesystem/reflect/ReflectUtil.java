package top.littlefogcat.demolist.knowledgesystem.reflect;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.function.Function;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ReflectUtil {

    public interface ExceptionHandler {
        void onException(Exception e);
    }

    /**
     * 获取Class的构造方法，如果没有匹配的，返回null
     */
    public static Constructor getConstructor(Class cls, Class... paramTypes) {
        return getConstructorInternal(cls, null, paramTypes);
    }

    /**
     * 获取Class的构造方法，如果没有匹配的，返回null
     */
    public static Constructor getConstructor(Class cls, ExceptionHandler handler, Class... paramTypes) {
        return getConstructorInternal(cls, handler, paramTypes);
    }

    /**
     * 获取Class的构造方法，如果没有匹配的，返回null
     */
    private static Constructor getConstructorInternal(Class cls, ExceptionHandler handler, Class... paramTypes) {
        try {
            Constructor c = cls.getDeclaredConstructor(paramTypes);
            c.setAccessible(true);
            return c;
        } catch (NoSuchMethodException ignore) {
        }
        Constructor[] constructors = cls.getDeclaredConstructors();
        out:
        for (Constructor c : constructors) {
            if (c.getParameterCount() != paramTypes.length) {
                continue;
            }
            for (int i = 0; i < paramTypes.length; i++) {
                Class cls1 = c.getParameterTypes()[i];
                Class cls2 = paramTypes[i];
                if (!typeEqual(cls1, cls2)) {
                    continue out;
                }
            }
            c.setAccessible(true);
            return c;
        }
        if (handler != null) {
            handler.onException(new NoSuchMethodException(
                    makeMethodStringDesc(cls.getName(), paramTypes)
            ));
        }
        return null;
    }

    private static String makeMethodStringDesc(String methodName, Class<?>... paramTypes) {
        StringBuilder builder = new StringBuilder(methodName);
        builder.append("(");
        if (paramTypes.length > 0) {
            builder.append(paramTypes[0].getName());
            for (int i = 1; i < paramTypes.length; i++) {
                builder.append(", ").append(paramTypes[i].getName());
            }
        }
        return builder.append(")").toString();
    }

    /**
     * 根据Class创建实例。如果失败，返回null。
     */
    public static <T> T newInstance(Class<T> cls, Object... params) {
        Class[] paramTypes = Arrays.stream(params)
                .map((Function<Object, Class>) Object::getClass)
                .toArray(value -> new Class[params.length]);
        Constructor<T> c = getConstructor(cls, paramTypes);
        try {
            return c.newInstance(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean typeEqual(Class c1, Class c2) {
        return c1 == c2 || wrapType(c1) == wrapType(c2);
    }

    /**
     * 将基本类型装箱
     */
    private static Class wrapType(Class cls) {
        if (cls == char.class) return Character.class;
        if (cls == byte.class) return Byte.class;
        if (cls == short.class) return Short.class;
        if (cls == int.class) return Integer.class;
        if (cls == long.class) return Long.class;
        if (cls == float.class) return Float.class;
        if (cls == double.class) return Double.class;
        return cls;
    }
}
