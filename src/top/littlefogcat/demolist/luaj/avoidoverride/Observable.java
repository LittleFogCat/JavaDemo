package top.littlefogcat.demolist.luaj.avoidoverride;

import org.luaj.vm2.LuaClosure;

public class Observable {
    static boolean flag = false;

    public void observe(String name, LuaClosure callback) {
        callback.call();
    }
}
