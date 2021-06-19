package top.littlefogcat.demolist.luaj;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class Scope {
    // 加载lua运行环境
    static final Globals globals = JsePlatform.standardGlobals();
    // init文件位置
//    static final String initPath = "src/top/littlefogcat/demolist/luaj/scripts/init.lua";
    // init文件位置
    static final String scopePath = "src/top/littlefogcat/demolist/luaj/scripts/scope.lua";
    static final String scope1Path = "src/top/littlefogcat/demolist/luaj/scripts/scope1.lua";
    static final String scope2Path = "src/top/littlefogcat/demolist/luaj/scripts/scope2.lua";

    public static void main(String[] args) {
        LuaValue chunk = globals.loadfile(scopePath);
        LuaValue chunk1 = globals.loadfile(scope1Path);
        LuaValue chunk2 = globals.loadfile(scope2Path);
        chunk.call();
        try {
            chunk1.call();
        } catch (Exception e) {
            System.err.println("catch Exception: " + e.getLocalizedMessage());
        }
        chunk2.call();
    }
}
