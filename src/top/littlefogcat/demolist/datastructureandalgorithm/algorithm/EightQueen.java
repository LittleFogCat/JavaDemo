package top.littlefogcat.demolist.datastructureandalgorithm.algorithm;

import java.util.Arrays;

public class EightQueen {

    public static class DFS {
        static int methodCount = 0;

        public static void main(String[] args) {
            int[][] board = new int[8][8];
            dfs(board, 0);
            System.out.println(methodCount);

//            board[1][3] = 1;
//            System.out.println(checkCanPut(board, 2, 4));
//            System.out.println(checkCanPut(board, 2, 2));
//            System.out.println(checkCanPut(board, 0, 2));
//            System.out.println(checkCanPut(board, 0, 4));
//            System.out.println(checkCanPut(board, 1, 4));
//            System.out.println(checkCanPut(board, 0, 3));
//            System.out.println(checkCanPut(board, 3, 4));
//            System.out.println(checkCanPut(board, 4, 7));
//            System.out.println(checkCanPut(board, 0, 5));
            System.out.println(Arrays.toString(t));
        }

        static int[] t = new int[8];

        public static void dfs(int[][] board, int row) {
            for (int i = 0; i < 8; i++) {
                if (checkCanPut(board, row, i)) {
                    if (row == 7) {
                        methodCount++;
                    } else {
                        int[][] nb = put(board, row, i);
                        dfs(nb, row + 1);
                    }
                }
            }
        }

        private static int[][] put(int[][] board, int row, int column) {
            int[][] nb = copy(board);
            nb[row][column] = 1;
            //todo
            return null;
        }

        private static int[][] copy(int[][] orig) {
            int[][] c = new int[orig.length][orig[0].length];
            for (int i = 0; i < orig.length; i++) {
                System.arraycopy(orig[i], 0, c[i], 0, orig[0].length);
            }
            return c;
        }

        private static boolean checkCanPut(int[][] board, int row, int column) {
            if (row > 7 || column > 7) return false;
            for (int i = 0; i < 8; i++) {
                if (board[row][i] == 1 || board[i][column] == 1) {
                    return false;
                }
                int j1 = row + column - i;
                int j2 = column - row + i;
                if (isInBoard(i, j1) && board[i][j1] == 1 ||
                        isInBoard(i, j2) && board[i][j2] == 1) return false;
            }
            return true;
        }

        private static boolean isInBoard(int row, int column) {
            return row >= 0 && row < 8 && column > 0 && column < 8;
        }
    }
}
