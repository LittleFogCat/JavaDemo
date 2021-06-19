package top.littlefogcat.demolist.luaj;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class LuaAndroid {
    private static final Globals globals = JsePlatform.standardGlobals();
    private List<LuaValue> chunks = new ArrayList<>();

    private LuaAndroid() {
    }

    public static LuaAndroid newInstance() {
        return new LuaAndroid();
    }

    public LuaAndroid load(String code) {
        LuaValue chunk = globals.load(code, "chunk-" + chunks.size());
        chunks.add(chunk);
        return this;
    }

    public LuaAndroid load(Reader code) {
        LuaValue chunk = globals.load(code, "chunk-" + chunks.size());
        chunks.add(chunk);
        return this;
    }

    public List<LuaValue> call() {
        List<LuaValue> result = new ArrayList<>();
        for (LuaValue chunk : chunks) {
            result.add(chunk.call());
        }
        return result;
    }
}
