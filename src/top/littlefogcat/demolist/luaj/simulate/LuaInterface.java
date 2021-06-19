package top.littlefogcat.demolist.luaj.simulate;

import org.luaj.vm2.LuaTable;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LuaInterface {
    public static final String DELAY = "delay";
    public static final String CLICK_AT = "";

    private ExecutorService mThread = Executors.newSingleThreadExecutor();


    public void delay(long millis) {
        mThread.execute(() -> {
            System.out.println("delay " + millis + "ms");
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void clickAt(int num) {
        mThread.execute(() -> System.out.println("click at " + num));
    }

    public LuaTable findViewByText(String text) {
        LuaTable map = new LuaTable();
        map.set(1, text + "id_1");
        map.set(2, text + "id_2");
        return map;
    }
}
