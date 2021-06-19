package top.littlefogcat.demolist.luaj.simulate;

import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;
import top.littlefogcat.demolist.luaj.Lua;

public class LuaCore {
    private static final Globals GLOBALS = JsePlatform.standardGlobals();
    private static final String path = Lua.getPath("simulate/lib.lua");

    public static void main(String[] args) {
        Lua.exec(GLOBALS, path);
    }
}
