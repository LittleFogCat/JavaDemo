package top.littlefogcat.demolist.base;

class CloneTest {

    static class A implements Cloneable {
        B b = new B();

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return b.name;
        }
    }

    static class B {
        String name = "bob";
    }

    public static void main(String[] args) throws Exception {
        A a = new A();
        A aClone = (A) a.clone();
        System.out.println(a);
        System.out.println(aClone);
        a.b.name = "alice";
        System.out.println(a);
        System.out.println(aClone);
    }
}
