package top.littlefogcat.demolist.datastructureandalgorithm.algorithm;

import java.util.Arrays;

public class Greed {

    public static class AssignCookies {
        public int findContentChildren(int[] g, int[] s) {
            int r = 0;
            Arrays.sort(g);
            Arrays.sort(s);
            for (int i = g.length - 1, j = s.length - 1; i >= 0 && j >= 0; i--) {
                if (g[i] <= s[j]) { // 不能满足
                    continue;
                }
                // 能满足
                j--;
                r++;
            }
            return r;
        }
    }


    public static class UpDownSequence {

        public int state = UNSPECIFIED;
        public static final int UNSPECIFIED = 0;
        public static final int UP = 1;
        public static final int DOWN = 2;

        public int wiggleMaxLength(int[] nums) {
            if (nums.length < 2) return nums.length;

            int maxLen = 1;
            for (int i = 1; i < nums.length; i++) {
                if (checkStateChanged(nums, i)) {
                    maxLen++;
                }
            }
            return maxLen;
        }

        public boolean checkStateChanged(int[] nums, int i) {
            if (nums[i] == nums[i - 1]) { // 无改变
                return false;
            }
            boolean changed;
            if (nums[i] < nums[i - 1]) { // 下降
                changed = state == UNSPECIFIED || state == UP;
                state = DOWN;
            } else { // 上升
                changed = state == UNSPECIFIED || state == DOWN;
                state = UP;
            }
            return changed;
        }
    }

}
