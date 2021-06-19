package top.littlefogcat.demolist.luaj;

import org.luaj.vm2.LuaClosure;
import org.luaj.vm2.LuaValue;

public class LuaJavaConnector {
    public static LuaClosure listener;

    public static void setListener(LuaClosure c) {
        listener = c;
    }

    public void sendMessage(String msg) {
        if (listener != null) {
            listener.call(LuaValue.valueOf(msg));
        }
    }
}
