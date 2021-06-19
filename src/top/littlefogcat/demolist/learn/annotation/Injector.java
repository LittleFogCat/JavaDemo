package top.littlefogcat.demolist.learn.annotation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Injector {
    private static final Map<String, Class<?>> injectNameMap = new HashMap<>();

    public static void init(ClassLoader classLoader, String pkgName) throws Exception {
        List<String> classList = ClassUtil.scanClasses(classLoader, pkgName);
        for (String className : classList) {
            Class<?> clazz = Class.forName(className);
            InjectName annotation = clazz.getAnnotation(InjectName.class);
            if (annotation != null) {
                String name = annotation.value();
                injectNameMap.put(name, clazz);
            }
        }
    }

    public static void inject(Object obj) throws Exception {
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getFields();
        for (Field field : fields) {
            if (field.getAnnotation(Autowired.class) != null) {
                // 这个字段标记了@Autowired
                Autowired a = field.getAnnotation(Autowired.class);
                String name = a.value(); // 获取要注入的name
                Class<?> clazz = injectNameMap.get(name); // 匹配要注入的类
                Object injectObj = clazz.newInstance(); // 要注入的对象
                field.setAccessible(true);
                field.set(obj, injectObj); // 注入
            }
        }
    }

    private static void inject(Object target, Field field) throws Exception {
        Class<?> fieldClass = field.getType();
        Object fieldObj = fieldClass.getConstructor().newInstance();
        field.setAccessible(true);
        field.set(target, fieldObj);
    }

}
