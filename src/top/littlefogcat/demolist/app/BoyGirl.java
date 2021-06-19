package top.littlefogcat.demolist.app;

import java.util.Random;

public class BoyGirl {
    public static void main(String[] args) {
        Random random = new Random();
        int boy = 0;
        int girl = 0;
        for (int i = 0; i < 100000000; i++) {
            while (true) {
                int r = random.nextInt(100);
                if (r < 50) {
                    boy++;
                    break;
                }
                girl++;
            }
        }

        System.out.println(boy + "," + girl);
    }
}
