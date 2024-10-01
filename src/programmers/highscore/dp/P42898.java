package programmers.highscore.dp;

public class P42898 {

    public static void main(String[] args) {
        int[][] puddles = {{4, 2}, {3, 3}};
        int solution = new Solution().solution(4, 3, puddles);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int x, int y, int[][] puddles) {
            int mod = 1_000_000_007;
            int[][] board = initBoard(x, y, puddles);

            for (int i = 1; i < y; i++) {
                for (int j = 1; j < x; j++) {
                    if (board[i][j] == -1) {
                        continue;
                    }
                    boolean topIsPuddle = board[i - 1][j] == -1;
                    boolean leftIsPuddle = board[i][j - 1] == -1;
                    if (topIsPuddle && leftIsPuddle) {
                    } else if (topIsPuddle) {
                        board[i][j] = board[i][j - 1];
                    } else if (leftIsPuddle) {
                        board[i][j] = board[i - 1][j];
                    } else {
                        board[i][j] = (board[i][j - 1] + board[i - 1][j]) % mod;
                    }
                }
            }

            return board[y - 1][x - 1];
        }

        private int[][] initBoard(int x, int y, int[][] puddles) {
            int[][] board = new int[y][x];
            for (int[] puddle : puddles) {
                int i = puddle[1] - 1;
                int j = puddle[0] - 1;
                board[i][j] = -1;
            }
            for (int i = 1; i < y && board[i][0] != -1; i++) {
                board[i][0] = 1;
            }
            for (int i = 1; i < x && board[0][i] != -1; i++) {
                board[0][i] = 1;
            }
            return board;
        }

    }
}
