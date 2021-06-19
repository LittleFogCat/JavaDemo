package top.littlefogcat.demolist.datastructureandalgorithm.algorithm;

public class DP {
    public static void main(String[] args) {
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
        int common = longestCommonSubsequence.longestCommonSubsequence("bce", "abced");
        System.out.println(common);
    }

    /**
     * s1: 我正在认真的学习怎么喜欢生孩子
     * s2: 牛牛喜欢我认真学习的样子
     * ->
     * s1: 我认真的学习喜欢子
     * s2: 喜欢我认真学习的子
     * ->
     * 我认真学习子
     */
    public static class LongestCommonSubsequence {
        public int longestCommonSubsequence(String text1, String text2) {
            char[] carr1 = text1.toCharArray();
            char[] carr2 = text2.toCharArray();
            // 保证 carr1 长于 carr2
            if (carr1.length < carr2.length) {
                char[] t = carr1;
                carr1 = carr2;
                carr2 = t;
            }

            return longestCommonSubsequence(carr1, carr2, 0);
        }

        public int longestCommonSubsequence(char[] carr1, char[] carr2, int ind2) {
            int maxLength = 0;

            

            return maxLength;
        }


        public int indexOf(char[] arr, char c) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == c) return i;
            }
            return -1;
        }
    }
}
