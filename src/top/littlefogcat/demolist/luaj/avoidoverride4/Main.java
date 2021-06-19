package top.littlefogcat.demolist.luaj.avoidoverride4;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JseBaseLib;
import org.luaj.vm2.lib.jse.JsePlatform;
import top.littlefogcat.demolist.utils.Timer;

import java.lang.instrument.Instrumentation;

public class Main {

    // 加载lua运行环境
    static final Globals globals = JsePlatform.standardGlobals();
    static final String libPath = "src/top/littlefogcat/demolist/luaj/avoidoverride4/lib.lua";
    static final String scope1Path = "src/top/littlefogcat/demolist/luaj/avoidoverride4/sc1.lua";
    static final String scope2Path = "src/top/littlefogcat/demolist/luaj/avoidoverride4/sc2.lua";

    static final String script1 = "foo = function()\n" +
            "    print(\"bar\")\n" +
            "end\n";
    static final String script2 = "foo()";

    static {
//        globals.loadfile(libPath).call();
    }

    public static void main(String[] args) {
//        Timer.printExecTime("same", () -> {
//            for (int i = 0; i < 100; i++) {
//                loadAndCall(scope1Path);
//                loadAndCall(scope2Path);
//            }
//        });
//
//        Timer.printExecTime("diff", () -> {
//            for (int i = 0; i < 100; i++) {
//                loadAndCallViaDifferentEnv(libPath, scope1Path);
//                loadAndCallViaDifferentEnv(libPath, scope2Path);
//            }
//        });
//        loadAndCallViaDifferentEnv2("script1", script1);
        loadAndCallViaDifferentEnv2("script2", script2);
    }

    static void loadAndCall(String scriptPath) {
        globals.loadfile(scriptPath).call();
    }

    static void loadAndCallViaDifferentEnv(String libPath, String scriptPath) {
        Globals globals = JsePlatform.standardGlobals();
        globals.loadfile(libPath).call();
        globals.loadfile(scriptPath).call();
    }

    static void loadAndCallViaDifferentEnv2(String name, String script) {
//        String stub = "local _ENV = {" + name + "}\n";
//        globals.load(stub).call();
        LuaValue lib = globals.loadfile(libPath);
        globals.load(script).load(lib).call();
    }

}
