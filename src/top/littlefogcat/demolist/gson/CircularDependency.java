package top.littlefogcat.demolist.gson;

import com.alibaba.fastjson.JSON;

public class CircularDependency {
    public static class A {
        public B b;
        public String name;
    }

    public static class B {
        public A a;
        public String name = "b";
    }

    public static class C {
        public String name = "c";
        public int age = 19;
    }

    public static void main(String[] args) {
        A a = new A();
        a.name = "a";
        B b = new B();
        b.name = "b";

        a.b = b;
        b.a = a;

        String json = JSON.toJSONString(a);
        System.out.println(json);
        A a1 = JSON.parseObject(json, A.class);
    }
}
