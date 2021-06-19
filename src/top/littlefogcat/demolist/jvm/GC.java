package top.littlefogcat.demolist.jvm;

public class GC {

    private static class GC1 {
        private static boolean flag = true;

        private static class A {
            private static class B {
                B() {
                    System.out.println("B created");
                }

                @Override
                protected void finalize() throws Throwable {
                    System.out.println("B recycled");
                    super.finalize();
                }
            }

            private static B b = new B();

            @Override
            protected void finalize() throws Throwable {
                System.out.println("A recycled");
                flag = false;
                super.finalize();
            }
        }

        private static void forceGCUntilRecycled() {
            new Thread(() -> {
                while (true) {
                    System.gc();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        public static void main(String[] args) {
            A a = new A();
            System.out.println("A.b -> " + A.b);
            forceGCUntilRecycled();
        }
    }

}
