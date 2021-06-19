package top.littlefogcat.demolist.luaj.base;

import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;
import top.littlefogcat.demolist.luaj.Lua;

import java.io.IOException;

public class LuaToJson {
    public static void main(String[] args) throws IOException {
        Globals globals = JsePlatform.standardGlobals();
        String path = Lua.getPath("base/app.lua");

        globals.loadfile(path).call();

//        LuaClosure chunk = (LuaClosure) globals.loadfile(path);
//        Prototype chunkP = chunk.p;
//        System.out.println("chunkP: " + JsonUtil.toJson(chunkP));
//
//        LuaValue table = null;
//        try {
//            table = chunk.call();
//        } catch (Exception e) {
//            System.out.println("message: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        LuaClosure func = (LuaClosure) table.get("foo1"); // 需要存储的函
//        System.out.println("f: " + func);
//        Prototype prototype = func.p; // 编译后的Lua代码
//        System.out.println("prototype: " + JsonUtil.toJson(prototype));
//        byte[] bytes = prototype.source.m_bytes;
//        System.out.println("bytes: " + new String(bytes));
//
//        PrototypeWrapper prototypeWrapper = new PrototypeWrapper(prototype);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(prototypeWrapper);
    }

}
