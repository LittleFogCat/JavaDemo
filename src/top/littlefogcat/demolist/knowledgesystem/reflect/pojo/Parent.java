package top.littlefogcat.demolist.knowledgesystem.reflect.pojo;

public class Parent extends Person {
    protected String name = "";

    public void hello() {
        System.out.println("hello");
    }

    protected void protectedMethod() {
        System.out.println("protectedMethod");
    }
}
