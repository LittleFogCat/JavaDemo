package top.littlefogcat.demolist.knowledgesystem.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;

@SuppressWarnings({"rawtypes", "MismatchedQueryAndUpdateOfCollection", "unchecked"})
public class GenericSample {

    public static class A<T> {

    }

    public static class B<T> extends A<T> {

    }

    public static class C extends B {

    }

    public void superSample(List<? super B> list) {
    }

    public void extendsSample(List<? extends B> list) {
    }

    public void noGeneric() {
        // 不使用泛型
        List nameList = new ArrayList();
        nameList.add("alice");
        nameList.add("bob");
        nameList.add(1);
        for (int i = 0; i < nameList.size(); i++) {
            String name = (String) nameList.get(i);
            System.out.println(name);
        }
    }

    public void wrongUse() {
        List list = new ArrayList();
        list.add("1");
        String str1 = (String) list.get(0);
        list.add(1);
        String str2 = (String) list.get(1);
        System.out.println(str1 + str2);
    }


    public static void main(String[]   args) {
        B<String> b = new B<>();
//        System.out.println(getGenericClassSupportInterface(b.getClass(), A.class, 0));
        ParameterizedType actual = (ParameterizedType) b.getClass().getGenericSuperclass();
        System.out.println(Arrays.toString(actual.getActualTypeArguments()));
        System.out.println(actual);
    }

    public static <T> Class getGenericClassSupportInterface(Class<? extends T> owner, Class<T> target, int index) {

        if (owner == target)
            return null;

        if (!target.isAssignableFrom(owner))
            return null;

        TypeVariable<Class<T>>[] tps = target.getTypeParameters();
        if (index >= tps.length)
            return null;

        @SuppressWarnings("FieldMayBeFinal")
        class Cache {

            private Cache(Class clazz) {
                this.clazz = clazz;
            }

            private Cache(Class clazz, int index) {
                this.clazz = clazz;
                this.index = index;
            }

            @Override
            public String toString() {
                return clazz.getName() + "-@-" + index;
            }

            private Class clazz;
            private int index = 0;
        }

        LinkedList<Cache> classes = new LinkedList<>();
        classes.add(new Cache(owner));
        Class nextClazz = owner;

        outside:
        while (true) {
            Class superClazz = nextClazz.getSuperclass();
            if (superClazz == target)
                break;
            if (superClazz != null && target.isAssignableFrom(superClazz)) {
                classes.add(new Cache(superClazz));
            } else {
                Class[] interfaces = nextClazz.getInterfaces();
                for (int i = 0; i < interfaces.length; i++) {
                    Class interfaca = interfaces[i];
                    if (interfaca == target)
                        break outside;
                    if (target.isAssignableFrom(interfaca)) {
                        classes.add(new Cache(interfaca, i));
                        superClazz = interfaca;
                        break;
                    }
                }
            }
            nextClazz = superClazz;
            if (superClazz == null)
                break;
        }

        Cache cache = classes.removeLast();
        Class supClazz = cache.clazz;

        Type typeVar;
        Class last = target;
        if (last.isInterface()) {
            typeVar = ((ParameterizedType) supClazz.getGenericInterfaces()[cache.index]).getActualTypeArguments()[index];
        } else {
            typeVar = ((ParameterizedType) supClazz.getGenericSuperclass()).getActualTypeArguments()[index];
        }

        if (typeVar instanceof Class)
            return (Class) typeVar;

        while (!classes.isEmpty()) {
            System.out.println(cache);
            index = Util.indexOf(supClazz.getTypeParameters(), typeVar, (left, right) -> {
                if (left == right)
                    return true;

                if (left == null || right == null)
                    return false;

                if (!(right instanceof TypeVariable))
                    return false;

                String leftName = left.getName();
                String rightName = ((TypeVariable) right).getName();

                return leftName.equals(rightName);
            });
            last = supClazz;
            cache = classes.removeLast();
            supClazz = cache.clazz;
            System.out.println(cache + "___");
            if (last.isInterface()) {
                typeVar = ((ParameterizedType) supClazz.getGenericInterfaces()[cache.index]).getActualTypeArguments()[index];
            } else {
                typeVar = ((ParameterizedType) supClazz.getGenericSuperclass()).getActualTypeArguments()[index];
            }
            if (typeVar instanceof Class)
                return (Class) typeVar;
        }
        return null;
    }

    static class Util {
        //获取数组 array 中 object 的角标
        public static <L, R> int indexOf(L[] array, R object, Comparator<L, R> comparator) {
            if (array == null)
                return -1;
            for (int i = 0; i < array.length; i++) {
                if (comparator.equals(array[i], object))
                    return i;
            }
            return -1;
        }

        interface Comparator<L, R> {
            boolean equals(L left, R right);
        }
    }
}

