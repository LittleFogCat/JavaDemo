package top.littlefogcat.demolist.luaj.base;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.lib.jse.JsePlatform;
import top.littlefogcat.demolist.luaj.Lua;

public class DisableGlobalVariables {
    public static void main(String[] args) {
        Globals globals = JsePlatform.standardGlobals();
        String path = Lua.getPath("/base/lib.lua");
        String path1 = Lua.getPath("/base/array_or_list2.lua");
        LuaTable t = (LuaTable) globals.loadfile(path).call();
        LuaFunction fFoo1 = (LuaFunction) t.get("foo1");
        fFoo1.call();
        System.out.println(fFoo1);
    }

}
