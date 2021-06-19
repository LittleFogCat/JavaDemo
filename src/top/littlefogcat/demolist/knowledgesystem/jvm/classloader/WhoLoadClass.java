//package top.littlefogcat.demolist.knowledgesystem.jvm.classloader;
//
//@SuppressWarnings("all")
//public class WhoLoadClass {
//    public static void main(String[] args) throws ClassNotFoundException {
//        String AClassName = "top.littlefogcat.demolist.base.jvm.classloader.ClsA";
//        String BClassName = "top.littlefogcat.demolist.base.jvm.classloader.ClsB";
//        Class AClass = Class.forName(AClassName); // 使用forName加载
//        Class BClass = ClassLoader.getSystemClassLoader().loadClass(BClassName); // 使用ClassLoader加载
//        System.out.println(AClass.getClassLoader().getName());
//        System.out.println(BClass.getClassLoader().getName());
//    }
//}
