package top.littlefogcat.demolist.learn.annotation;

import top.littlefogcat.demolist.learn.annotation.annotations.Autowired;
import top.littlefogcat.demolist.learn.annotation.annotations.Bean;
import top.littlefogcat.demolist.learn.annotation.annotations.Component;
import top.littlefogcat.demolist.learn.annotation.util.ClassUtil;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 对象容器
 */
public class ObjectsContainer {
    private static final Map<String, ObjectsContainer> containerMap = new HashMap<>();

    private final String packageName;
    private final Map<String, Object> beans = new HashMap<>(); // 保存实例对象
    private final Map<Class<?>, Object> components = new HashMap<>(); // 组件类，组件中可实现自动注入

    private ObjectsContainer(String packageName) {
        this.packageName = packageName;
        scanAnnotations();
    }

    public static ObjectsContainer get(String packageName) {
        if (!containerMap.containsKey(packageName)) {
            containerMap.put(packageName, new ObjectsContainer(packageName));
        }
        return containerMap.get(packageName);
    }

    // 扫描包下所有标注了@Bean和@Component的类
    private void scanAnnotations() {
        List<String> classList = ClassUtil.scanClasses(getClass(), packageName);
        Set<Class<?>> componentClasses = new HashSet<>();
        if (classList != null) {
            for (String className : classList) {
                try {
                    Class<?> clazz = Class.forName(className);
                    Component componentAnno = clazz.getAnnotation(Component.class);
                    if (componentAnno != null) {
                        componentClasses.add(clazz);
                    }
                    Bean beanAnno = clazz.getAnnotation(Bean.class);
                    if (beanAnno != null) {
                        // 注册bean
                        String beanName = beanAnno.name();
                        if (beans.containsKey(beanName)) {
                            throw new DuplicateBeanNameException(beanName, clazz, beans.get(beanName).getClass());
                        }
                        beans.put(beanName, clazz.newInstance());
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.err.println("No classes found in package: " + packageName);
        }
        for (Class<?> clazz : componentClasses) {
            // 注册组件
            try {
                Object component = clazz.newInstance();
                Field[] fields = clazz.getFields();
                for (Field field : fields) {
                    Autowired beanAnno = field.getAnnotation(Autowired.class);
                    if (beanAnno != null) {
                        Object bean = beans.get(beanAnno.value());
                        if (bean == null) {
                            throw new NullPointerException("Bean named `" + beanAnno.value() + "` is null.");
                        }
                        field.setAccessible(true);
                        field.set(component, bean);
                    }
                }
                components.put(clazz, component);
            } catch (Exception e) {
                Exception exception = new RuntimeException("Component `" + clazz + "` create failed.", e);
                exception.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getComponent(String clazzName) {
        Class<?> clazz;
        try {
            clazz = Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            throw new NoSuchElementException("Component named `" + clazzName + "` has not been registered. " +
                    "Use @Component annotation to register a component.");
        }
        return (T) getComponent(clazz);
    }

    @SuppressWarnings("unchecked")
    public <T> T getComponent(Class<T> clazz) {
        if (!components.containsKey(clazz)) {
            throw new NoSuchElementException("Component class `" + clazz + "` has not been registered. " +
                    "Use @Component annotation to register a component.");
        }
        return (T) components.get(clazz);
    }

    /**
     * 获取名为{@code name}的组件
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(String name) {
        if (!beans.containsKey(name)) {
            throw new NoSuchElementException("Bean named `" + name + "` has not been registered. " +
                    "Use @Bean annotation to register a bean.");
        }
        return (T) beans.get(name);
    }

    public static class DuplicateBeanNameException extends RuntimeException {
        public DuplicateBeanNameException(String name, Class<?> c1, Class<?> c2) {
            this("Duplicated @Bean class named `" + name + "`. " +
                    "Duplicated classes = [" + c1.getName() + ", " + c2.getName() + "]");
        }

        public DuplicateBeanNameException(String msg) {
            super(msg);
        }
    }
}
