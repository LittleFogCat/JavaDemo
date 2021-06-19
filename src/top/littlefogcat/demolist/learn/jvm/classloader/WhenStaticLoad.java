package top.littlefogcat.demolist.learn.jvm.classloader;

public class WhenStaticLoad {
    private static WhenStaticLoad instance = new WhenStaticLoad();
    public static int i;

    WhenStaticLoad() {
        System.out.println("construct");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello");
        Thread.sleep(1000);
        System.out.println(A.i);
    }

    public static class A {
        static int i = 0;
        private static A a = new A();

        A() {
            System.out.println("A");
        }
    }
}
