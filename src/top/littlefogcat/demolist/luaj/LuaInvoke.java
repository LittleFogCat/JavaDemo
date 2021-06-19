package top.littlefogcat.demolist.luaj;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class LuaInvoke {
    // 加载lua运行环境
    static final Globals globals = JsePlatform.standardGlobals();
    // init文件位置
//    static final String initPath = "src/top/littlefogcat/demolist/luaj/scripts/init.lua";
    // init文件位置
    static final String scopePath = "src/top/littlefogcat/demolist/luaj/scripts/network.lua";

    public static void main(String[] args) {
        LuaValue arg1 = LuaValue.valueOf("1");
        LuaValue arg2 = LuaValue.valueOf("2");
        LuaValue arg3 = LuaValue.valueOf("3");
        LuaValue chunk = globals.loadfile(scopePath);
//        chunk.call(arg1, arg2, arg3);
        chunk.invoke(new LuaValue[]{arg1, arg2, arg3});
    }
}
