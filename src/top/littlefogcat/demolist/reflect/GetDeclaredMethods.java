package top.littlefogcat.demolist.reflect;

import java.util.Arrays;

public class GetDeclaredMethods {
    public static void main(String[] args) {
        Class<?> c = GetDeclaredMethods.class;
        System.out.println(Arrays.toString(c.getDeclaredMethods()));
    }

    public void publicMethod() {
    }

    protected void protectedMethod() {
    }

    void defaultMethod() {
    }

    private void privateMethod() {
    }

}
