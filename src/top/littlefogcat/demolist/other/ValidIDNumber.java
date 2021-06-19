package top.littlefogcat.demolist.other;

public class ValidIDNumber {
    private static final int[] w = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static final char[] y = new char[]{'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    public static boolean isValid(String s) {
        if (s.length() != 18) return false;
        char[] str = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < str.length - 1; i++) {
            sum += (str[i] - '0') * w[i];
        }
        int c = str[str.length - 1];
        return y[sum % 11] == c;
    }

    public static void main(String[] args) {
        String format = "3621021980%02d%02d0038";
        for (int M = 1; M <= 12; M++) {
            for (int d = 1; d <= 31; d++) {
                String id = String.format(format, M, d);
                if (isValid(id)) System.out.println(id);
            }
        }
    }
}
