package top.littlefogcat.demolist.luaj.threads;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class Observable {
    public static void main(String[] args) {
        Globals globals = JsePlatform.standardGlobals();
        LuaValue script = globals.loadfile("src/top/littlefogcat/demolist/luaj/threads/script.lua");
        script.call();
    }

    public static void run(LuaValue luaValue) {
        System.out.println(luaValue);
    }
}
