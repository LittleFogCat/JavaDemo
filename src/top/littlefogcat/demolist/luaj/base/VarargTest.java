package top.littlefogcat.demolist.luaj.base;

import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;
import top.littlefogcat.demolist.luaj.Lua;
import top.littlefogcat.demolist.luaj.LuaUtils;

public class VarargTest {

    public static class Notification {
        public Intent intent = new Intent();

        @Override
        public String toString() {
            return "Notification{" +
                    "intent=" + intent +
                    '}';
        }

        public LuaValue toLuaValue() {
            return CoerceJavaToLua.coerce(this);
        }
    }

    public static class Intent {
        public void send() {
            System.out.println("callback intent send");
        }
    }

    public static final String PATH = Lua.getPath("base/vararg_test.lua");
    public static final Globals GLOBALS = JsePlatform.standardGlobals();
    private static LuaClosure callback;

    public static void main(String[] args) throws InterruptedException {
        LuaClosure func = (LuaClosure) GLOBALS.loadfile(PATH).call();
        Thread.sleep(1000);
        Notification notification = new Notification();
        callback.invoke(notification.toLuaValue());
    }

    public static void varargFun(String type, Object... args) {
        System.out.println("type: " + type);
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            System.out.print("arg" + i + ": " + arg + "/" + arg.getClass().getSimpleName() + "   ");
        }
        System.out.println();
    }

    public static void setTrigger(int type, LuaClosure action, LuaTable args) {
        System.out.println("setTrigger: type = " + type + ", action = " + action + ", args = " + LuaUtils.luaTableToMap(args));
        callback = action;
    }
}
