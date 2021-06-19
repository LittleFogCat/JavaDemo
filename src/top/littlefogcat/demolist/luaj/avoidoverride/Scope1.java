package top.littlefogcat.demolist.luaj.avoidoverride;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class Scope1 {
    // 加载lua运行环境
    static final Globals globals = JsePlatform.standardGlobals();
    // init文件位置
//    static final String initPath = "src/top/littlefogcat/demolist/luaj/scripts/init.lua";
    // init文件位置
    static final String scopePath = "src/top/littlefogcat/demolist/luaj/avoidoverride/lib.lua";
    static final String scope1Path = "src/top/littlefogcat/demolist/luaj/avoidoverride/sc1.lua";
    static final String scope2Path = "src/top/littlefogcat/demolist/luaj/avoidoverride/sc2.lua";

    public static void main(String[] args) throws InterruptedException {
        LuaValue initScript = globals.loadfile(scopePath);

        LuaValue chunk1 = globals.loadfile(scope1Path);
        chunk1.load(initScript);
        chunk1.call();

        LuaValue chunk2 = globals.loadfile(scope2Path);
        chunk2.load(initScript);
        chunk2.call();
    }

}
