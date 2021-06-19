package top.littlefogcat.demolist.luaj;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;

public class Lua {
    public static final String BASE = "src/top/littlefogcat/demolist/luaj/";

    public static String getPath(String relative) {
        return BASE + relative;
    }

    public static LuaValue exec(Globals globals, String file) {
        return globals.loadfile(file).call();
    }
}
