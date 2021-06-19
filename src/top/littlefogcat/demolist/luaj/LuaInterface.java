package top.littlefogcat.demolist.luaj;

import org.luaj.vm2.LuaClosure;
import org.luaj.vm2.LuaValue;

/**
 * Lua调用Java接口
 */
public class LuaInterface {

    public void call(LuaClosure callback) {
        callback.call(LuaValue.valueOf("hello from Java"));
    }

    public String getClassName() {
        return getClass().getSimpleName();
    }
}
