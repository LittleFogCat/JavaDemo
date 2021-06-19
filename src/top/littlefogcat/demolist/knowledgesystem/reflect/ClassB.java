package top.littlefogcat.demolist.knowledgesystem.reflect;

public class ClassB extends ClassA {
    public String name = "bob";
    private int age = 18;

    public void publicB() {
        System.out.println("publicB");
    }

    private void privateB() {
        System.out.println("privateB");
    }
}
