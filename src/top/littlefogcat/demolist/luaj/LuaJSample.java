package top.littlefogcat.demolist.luaj;

import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.IOException;
import java.net.URISyntaxException;

public class LuaJSample {
    // 加载lua运行环境
    static final Globals globals = JsePlatform.standardGlobals();
    // init文件位置
    static final String initPath = "src/top/littlefogcat/demolist/luaj/scripts/init.lua";
    // sample文件位置
    static final String samplePath = "src/top/littlefogcat/demolist/luaj/scripts/sample.lua";

    public static void main(String[] args) throws IOException, URISyntaxException {
        globals.loadfile(initPath).call(); // 加载初始化脚本
        globals.loadfile(samplePath).call(); // 加载sample脚本
    }
}
