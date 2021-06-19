package top.littlefogcat.demolist.knowledgesystem.base;

public class InnerClasses {
    interface I {
        void run();
    }

    class Dad {
        void run() {
        }
    }

    class A extends Dad implements I {
        @Override
        public void run() {

        }
    }
}
