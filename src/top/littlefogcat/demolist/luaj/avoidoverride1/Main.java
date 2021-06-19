package top.littlefogcat.demolist.luaj.avoidoverride1;

import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;

public class Main {
    public static void main(String[] args) {
        Globals globals = JsePlatform.standardGlobals();
        globals.loadfile("src/top/littlefogcat/demolist/luaj/avoidoverride1/lib.lua").call();
        globals.loadfile("src/top/littlefogcat/demolist/luaj/avoidoverride1/s1.lua").call();
        globals.loadfile("src/top/littlefogcat/demolist/luaj/avoidoverride1/s2.lua").call();
    }
}
