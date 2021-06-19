package top.littlefogcat.demolist.game.dkp.lm;

public class Spec {
    public static final int ZF_OFFSET = 16;
    public static final int LD_OFFSET = 8;
    public static final int LS_OFFSET = 0;

    public int val;
    public int zf, ld, ls;

    public Spec(int spec) {
        val = spec;
        zf = ZF(spec);
        ld = LD(spec);
        ls = LS(spec);
    }

    public static int makeSpec(int ZF, int LD, int LS) {
        return (ZF << ZF_OFFSET) |
                (LD << LD_OFFSET) |
                (LS << LS_OFFSET);
    }

    public static int ZF(int spec) {
        return (spec >> ZF_OFFSET) & 0xFF;
    }

    public static int LD(int spec) {
        return (spec >> LD_OFFSET) & 0xFF;
    }

    public static int LS(int spec) {
        return (spec >> LS_OFFSET) & 0xFF;
    }
}
