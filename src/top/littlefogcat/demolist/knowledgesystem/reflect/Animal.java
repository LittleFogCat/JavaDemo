package top.littlefogcat.demolist.knowledgesystem.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 反射
class Animal {
    private String name;

    private Animal() {

    }

    public Animal(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name + " is running");
    }

    private void die() {
        System.out.println(name + " died");
    }

    public String getName() {
        return name;
    }
}

@SuppressWarnings({"", "rawtypes", "unchecked"})
class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Animal animal = new Animal("duck"); // 创建Animal对象
//        Class cls = Animal.class;
//        System.out.println(Arrays.toString(Arrays.stream(cls.getConstructors()).map(Constructor::getName).toArray()));
//        System.out.println(Arrays.toString(Arrays.stream(cls.getDeclaredConstructors())
//                .map(constructor -> Arrays.asList(constructor.getParameterTypes()))
//                .toArray()));
        invokePrivateMethod(animal);
    }

    // 通过反射修改name
    private static void modifyName(Animal animal) throws NoSuchFieldException, IllegalAccessException {
        Class cls = Animal.class; // 获取Class对象
        Field nameField = cls.getDeclaredField("name"); // 获取Field对象，即成员变量
        nameField.setAccessible(true); // 设置name权限为可获取
        nameField.set(animal, "pig"); // 修改animal的名称为"pig"
        System.out.println(animal.getName()); // 打印名称
    }

    // 通过反射调用私有方法
    private static void invokePrivateMethod(Animal animal) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class cls = Animal.class; // 获取Class对象
        Method method = cls.getDeclaredMethod("die"); // 获取Method对象
        method.setAccessible(true); // 设置方法可以调用
        method.invoke(animal); // 调用方法
    }

    // 通过私有的无参构造方法创建Animal对象
    private static Animal constructInstance() throws Exception {
        Class cls = Animal.class; // 获取Class对象
        Constructor constructor = cls.getDeclaredConstructor(); // 获取无参构造方法
        constructor.setAccessible(true); // 设置该构造方法可调用
        return (Animal) constructor.newInstance(); // 创建实例并返回
    }
}