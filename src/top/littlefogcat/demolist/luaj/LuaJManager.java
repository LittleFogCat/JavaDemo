package top.littlefogcat.demolist.luaj;

public class LuaJManager {
    private static final LuaJManager sInstance = new LuaJManager();

    public static LuaJManager getInstance(){
        return sInstance;
    }

    public void sample(){
        System.out.println("LuaJManager.java: sample() invoked");
    }
}
