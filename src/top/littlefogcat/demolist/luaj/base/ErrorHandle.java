package top.littlefogcat.demolist.luaj.base;

import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;

public class ErrorHandle {
    public static void main(String[] args) {
        Globals globals = JsePlatform.standardGlobals();
        String code = "error(\"Don't change that!\")";
        globals.load(code).call();
    }
}
