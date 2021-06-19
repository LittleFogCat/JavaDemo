package top.littlefogcat.demolist.luaj;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;

import java.util.HashMap;
import java.util.Map;

public class LuaUtils {
    public static Map<Object, Object> luaTableToMap(LuaTable table) {
        Map<Object, Object> map = new HashMap<>();
        LuaValue k = LuaValue.NIL;
        while (true) {
            Varargs n = table.next(k);
            if ((k = n.arg1()).isnil())
                break;
            LuaValue v = n.arg(2);
            map.put(parseLuaValue(k), parseLuaValue(v));
        }
        return map;
    }

    public static Object parseLuaValue(LuaValue luaValue) {
        switch (luaValue.type()) {
            case LuaValue.TNIL:
                return null;
            case LuaValue.TTABLE:
                return luaTableToMap((LuaTable) luaValue);
            case LuaValue.TSTRING:
                return luaValue.tojstring();
            case LuaValue.TBOOLEAN:
                return luaValue.toboolean();
            case LuaValue.TNUMBER:
                return luaValue.tofloat();
            default:
                return luaValue;
        }
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> luaTableToMapUnchecked(LuaTable table) {
        Map<K, V> map = new HashMap<>();
        LuaValue k = LuaValue.NIL;
        while (true) {
            Varargs n = table.next(k);
            if ((k = n.arg1()).isnil())
                break;
            LuaValue v = n.arg(2);
            K key = (K) parseLuaValueUnchecked(k);
            V value = (V) parseLuaValueUnchecked(v);
            map.put(key, value);
        }
        return map;
    }

    public static Object parseLuaValueUnchecked(LuaValue luaValue) {
        switch (luaValue.type()) {
            case LuaValue.TNIL:
                return null;
            case LuaValue.TTABLE:
                return luaTableToMapUnchecked((LuaTable) luaValue);
            case LuaValue.TSTRING:
                return luaValue.tojstring();
            case LuaValue.TBOOLEAN:
                return luaValue.toboolean();
            case LuaValue.TNUMBER:
                return luaValue.tofloat();
            default:
                return luaValue;
        }
    }

}
