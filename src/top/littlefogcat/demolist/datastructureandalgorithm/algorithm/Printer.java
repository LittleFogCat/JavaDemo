package top.littlefogcat.demolist.datastructureandalgorithm.algorithm;

import java.util.Arrays;

public class Printer {
    public static void print(Object... args) {
        StringBuilder s = new StringBuilder(args[0].toString());
        for (int i = 1; i < args.length; i++) {
            Object argi = args[i];
            if (argi instanceof int[]) {
                s.append(", ").append(Arrays.toString((int[]) argi));
            } else {
                s.append(", ").append(argi);
            }
        }
        System.out.println(s);
    }
}
