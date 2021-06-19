package top.littlefogcat.demolist.knowledgesystem.jvm.classloader;

/**
 * Class.forName()和ClassLoader.loadClass()的区别
 * <p>
 * forName会初始化static块，ClassLoader不会
 */
@SuppressWarnings("all")
public class LoadClassByForNameAndClassLoader {
    public static void main(String[] args) throws Exception {
        String className = "top.littlefogcat.demolist.base.jvm.classloader.MyClass";
        Class.forName(className);

//        ClassLoader cl = ClassLoader.getSystemClassLoader();
//        cl.loadClass(className);

//        System.out.println("caller: " + getCallerClass());
    }

//    private static Class getCallerClass() throws Exception {
//        Class reflectionClass = Class.forName("jdk.internal.reflect.Reflection");
//        Method getCallerClassMethod = reflectionClass.getDeclaredMethod("getCallerClass");
//        getCallerClassMethod.setAccessible(true);
//        return (Class) getCallerClassMethod.invoke(null);
//    }
}
