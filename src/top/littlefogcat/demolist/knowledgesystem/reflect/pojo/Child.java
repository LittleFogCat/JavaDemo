package top.littlefogcat.demolist.knowledgesystem.reflect.pojo;

public class Child extends Parent {
    private int i = 0;

    Child() {
    }

    Child(int i) {
        this.i = i;
    }

    public void getNum() {
        System.out.println("i = " + i);
    }

    public void world() {
        System.out.println("world");
    }

    private void privateMethod() {
        System.out.println("privateMethod");
    }
}
