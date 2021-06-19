package top.littlefogcat.demolist.knowledgesystem.concurrent;

import static top.littlefogcat.demolist.knowledgesystem.concurrent.Utils.sleep;

public class Homework {
    public Homework doChineseByRed() {
        sleep(3000);
        System.out.println("小红做完了语文作业，用时3秒");
        return new Homework();
    }

    public Homework doMathByGreen() {
        sleep(2000);
        System.out.println("小绿做完了数学作业，用时2秒");
        return new Homework();
    }

    public Homework doEnglish() {
        sleep(1000);
        System.out.println("小明做完了英语作业，用时1秒");
        return new Homework();
    }

    public void play() {
        System.out.println("小明终于可以玩了！");
    }

    public static void handIn(Homework... homework) {
        System.out.println("交作业");
    }
}
