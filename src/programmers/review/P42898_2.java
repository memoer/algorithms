package programmers.review;

import java.util.Arrays;

public class P42898_2 {

    public static void main(String[] args) {
        int solution = new Solution().solution(4, 3, new int[][]{{2, 2}});
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int m, int n, int[][] puddles) {
            int[][] board = create(m, n, puddles);

            for (int y = 0; y < n; y++) {
                for (int x = 0; x < m; x++) {
                    if (board[y][x] == -1) {
                        continue;
                    }

                    if (x + 1 < m && board[y][x + 1] != -1) {
                        board[y][x + 1] += 1;
                    } else if (y + 1 < n && board[y + 1][x] != -1) {
                        board[y + 1][x] += 1;
                    }
                }
            }
            return board[n - 1][m - 1] % 1_000_000_007;
        }

        private int[][] create(int m, int n, int[][] puddles) {
            int[][] board = new int[n][m];
            for (int[] ints : board) {
                Arrays.fill(ints, 0);
            }
            for (int[] p : puddles) {
                int y = p[0] - 1;
                int x = p[1] - 1;
                board[y][x] = -1;
            }
            return board;
        }

    }
}
