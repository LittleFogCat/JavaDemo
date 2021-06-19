package top.littlefogcat.demolist;

public class Global {
    public static void sleep(long mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
