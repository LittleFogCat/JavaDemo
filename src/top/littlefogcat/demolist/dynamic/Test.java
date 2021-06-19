package top.littlefogcat.demolist.dynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class Test {
    public static final String CODE =
            "package top.littlefogcat.demolist.dynamic;\n" +
                    "\n" +
                    "public class Sample {\n" +
                    "    public static void sayHello() {\n" +
                    "        System.out.println(\"hello world\");\n" +
                    "    }\n" +
                    "}";

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        DynamicCodeCompiler compiler = new DynamicCodeCompiler(CODE, "top.littlefogcat.demolist.dynamic.Sample", "C:\\cache");
        Class<?> cls = compiler.compileAndLoadClass();
        Method sayHello = cls.getMethod("sayHello");
        sayHello.invoke(cls.newInstance());
    }
}
