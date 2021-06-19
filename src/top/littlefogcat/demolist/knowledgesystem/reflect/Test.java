package top.littlefogcat.demolist.knowledgesystem.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Test {
    public static void main(String[] args) throws Throwable {
//        Method printPerson = Person.class.getMethod("printPerson", Person.class);
//        printPerson.invoke(null, new Person());
//        printPerson.invoke(null, new Parent());

//        Constructor<?> c = ReflectUtil.getConstructor(Person.class);
//        Person p = ReflectUtil.newInstance(Person.class);
//        System.out.println(c);
//        System.out.println(p);

        ClassB b = new ClassB();
        Class aClass = ClassA.class;
        Class bClass = ClassB.class;

        System.out.println(Arrays.toString(Arrays.stream(bClass.getMethods()).map(Method::getName).toArray()));
        System.out.println(Arrays.toString(Arrays.stream(bClass.getDeclaredMethods()).map(Method::getName).toArray()));

        Field nameField = bClass.getDeclaredField("name");
        nameField.setAccessible(false);
        nameField.set(b, "ketty");
        System.out.println(nameField.get(b));
        Field ageField = bClass.getDeclaredField("age");
        ageField.setAccessible(true);
        System.out.println(ageField.getInt(b));

        Method methodB = bClass.getDeclaredMethod("privateB");
        methodB.setAccessible(true);
        methodB.invoke(b);

        Constructor constructor = bClass.getDeclaredConstructor();
        constructor.newInstance();

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle handle = lookup.findSpecial(ClassA.class, "privateA", MethodType.methodType(void.class), ClassA.class);
        handle.invoke(b);

        Test test = new Test();
        test.test();
//        MethodHandles.Lookup lookup = MethodHandles.lookup();
//        MethodHandle handle = lookup.findSpecial(Test.class, "test", MethodType.methodType(void.class), Test.class);
//        handle.invoke(test);

    }

    private void test() {
        System.out.println("test");
    }

}
