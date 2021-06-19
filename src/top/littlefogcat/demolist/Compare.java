package top.littlefogcat.demolist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Compare {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 6, 9, 2, 5, 4));

        list.sort((o1, o2) -> o2 - o1);

        System.out.println(list);
    }
}
