package top.littlefogcat.demolist.luaj;

import org.luaj.vm2.LuaClosure;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Prototype;

public abstract class LuaCallback extends LuaClosure {
    public LuaCallback(Prototype prototype, LuaValue luaValue) {
        super(prototype, luaValue);
    }

    abstract void onCallback(String s);
}
