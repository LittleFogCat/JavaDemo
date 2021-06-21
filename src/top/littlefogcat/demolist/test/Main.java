package top.littlefogcat.demolist.test;

import top.littlefogcat.demolist.learn.annotation.util.ClassUtil;

class Main {
    public static void main(String[] args) {
        System.out.println(ClassUtil.scanClasses(Main.class, "top.littlefogcat.demolist"));
    }
}
