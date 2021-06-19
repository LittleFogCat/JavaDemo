package top.littlefogcat.demolist.luaj;

import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Closure {
    // 加载lua运行环境
    static final Globals globals = JsePlatform.standardGlobals();
    // init文件位置
    static final String initPath = "src/top/littlefogcat/demolist/luaj/scripts/android.lua";

    public static void main(String[] args) throws IOException, URISyntaxException {
        globals.loadfile(initPath).call(); // 加载初始化脚本
        Scanner scanner = new Scanner(System.in);
        LuaJavaConnector connector = new LuaJavaConnector();
        while (true) {
            System.out.print("等待输入：");
            String input = scanner.nextLine(); // 模拟通知
            connector.sendMessage(input); // 发送给Lua
        }
    }
}
