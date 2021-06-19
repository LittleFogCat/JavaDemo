package top.littlefogcat.demolist.datastructureandalgorithm.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PoolMoneyProblem {
    public static abstract class Solution {
        int moneyToPool;
        int[] values;
        int[] counts;

        Solution(int moneyToPool, int[] values, int[] counts) {
            this.moneyToPool = moneyToPool;
            this.values = Arrays.copyOf(values, values.length);
            this.counts = Arrays.copyOf(counts, counts.length);
        }

        abstract int[] solute() throws SolutionException;
    }

    public static void main(String[] args) {
        int moneyToPool = 146;
        int[] values = integer(1, 2, 5, 10, 20, 50, 100);
        int[] counts = integer(0, 3, 1, 2, 3, 3, 2);
//        print(moneyToPool, values, counts);

        Solution greed = new Greed(moneyToPool, values, counts);
        soluteAndPrint(greed);

        Solution dynamic = new Dynamic(moneyToPool, values, counts);
        soluteAndPrint(dynamic);
    }

    private static int[] integer(int... ints) {
        return ints;
    }

    private static void soluteAndPrint(Solution s) {
        try {
            int[] result = s.solute();
            if (result == null) throw new SolutionException("No way!");
            System.out.println(s.getClass().getSimpleName() + ": " + Arrays.toString(result));
        } catch (SolutionException e) {
            System.err.println(s.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    /**
     * 检查输入是否正确
     *
     * @param money
     * @param values
     * @param counts
     * @return
     */
    public static int[] checkInput(int money, int[] values, int[] counts) throws SolutionException {
        if (values == null || counts == null || values.length < counts.length) {
            throw new SolutionException("Wrong Args");
        }
        int sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i] * counts[i];
        }
        if (sum < money) {
            throw new IllegalArgumentException("checkInput: sum < money");
        } else if (sum == money) {
            return Arrays.copyOf(counts, counts.length);
        }
        return null;
    }

    /**
     * 例题1：钱币找零问题
     * 1、题目：指定币值和相应的数量，用最少的数量凑齐某金额。
     * 2、思路：利用贪心算法，我们优先选择面值大的钱币，以此类推，直到凑齐总金额。
     * 3、算法实现：
     * // 贪心算法
     */
    public static class Greed extends Solution {


        Greed(int moneyToPool, int[] values, int[] counts) {
            super(moneyToPool, values, counts);
        }

        /**
         * 例题1：钱币找零问题
         * 1、题目：指定币值和相应的数量，用最少的数量凑齐某金额。
         * 2、思路：利用贪心算法，我们优先选择面值大的钱币，以此类推，直到凑齐总金额。
         * 3、算法实现：
         *
         * @param money  要凑多少钱
         * @param values 有哪些币种（按从小到大）
         * @param counts 每种币分别有多少张
         * @return 每种币分别多少张
         */
        public int[] makeCount(int money, int[] values, int[] counts) throws SolutionException {
            int[] check = checkInput(money, values, counts);
            if (check != null) return check;

            if (values.length > counts.length) {
                int[] newCounts = new int[values.length];
                System.arraycopy(counts, 0, newCounts, 0, counts.length);
                counts = newCounts;
            }
            int[] result = new int[values.length];
            while (money > 0) {
                for (int i = values.length - 1; i >= 0; i--) {
                    int value = values[i];
                    if (value <= money && counts[i] > 0) {
                        money -= value;
                        counts[i]--;
                        result[i]++;
                        break;
                    } else if (i == 0) {
                        throw new SolutionException("No way!");
                    }
                }
            }
            return result;
        }

        public int[] getNumber1(int money, int[] values, int[] counts) throws SolutionException {
            int[] check = checkInput(money, values, counts);
            if (check != null) return check;

            int[] result = new int[7];
            int add = 0; //当前凑的金额
            for (int i = values.length - 1; i >= 0; i--) {
                int num = (money - add) / values[i];
                if (num > counts[i]) {
                    num = counts[i];
                }
                add = add + num * values[i];
                result[i] = num;
            }
            return result;
        }

        @Override
        public int[] solute() throws SolutionException {
            return makeCount(moneyToPool, values, counts);
        }
    }

    public static class Dynamic extends Solution {

        Dynamic(int moneyToPool, int[] values, int[] counts) {
            super(moneyToPool, values, counts);
        }

        @Override
        public int[] solute() {
            int[] moneys = generateMoneyList();
            List<Integer> result = dp(moneyToPool, moneys, 0);
            if (result != null) {
                int[] resultArr = new int[result.size()];
                for (int i = 0; i < result.size(); i++) {
                    resultArr[i] = result.get(i);
                }
                return resultArr;
            }
            return null;
        }

        private List<Integer> dp(int rest, int[] moneys, int cur) {
            if (cur == moneys.length || rest < 0) return null;
            int current = moneys[cur];
            if (rest == current) {
                List<Integer> l = new ArrayList<>();
                l.add(rest);
                return l;
            } else {
                List<Integer> dpResult = dp(rest - current, moneys, cur + 1);
                if (dpResult == null) {
                    dpResult = dp(rest, moneys, cur + 1);
                } else {
                    dpResult.add(current);
                }
                return dpResult;
            }
        }

        private int[] generateMoneyList() {
            int sum = 0;
            for (int item : counts) {
                sum += item;
            }
            int[] moneys = new int[sum];
            int p = sum - 1;

            for (int i = 0; i < values.length; i++) {
                int value = values[i];
                int count = counts[i];
                for (int j = 0; j < count; j++) {
                    moneys[p--] = value;
                }
            }
            return moneys;
        }

    }

}
