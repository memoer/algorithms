package programmers.Lv2;

import java.util.ArrayDeque;
import java.util.Deque;

public class P1829_2 {

    public static void main(String[] args) {
        int m = 6, n = 4;
        int[][] picture = {
            {1, 1, 1, 0},
            {1, 2, 2, 0},
            {1, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 3},
            {0, 0, 0, 3}
        };
        int[] solution = new Solution().solution(m, n, picture);
        System.out.println(solution[0] + ", " + solution[1]);
    }

    private static class Solution {

        boolean[][] visited;
        private int[][] board;
        private int m;
        private int n;

        public int[] solution(int m, int n, int[][] picture) {
            this.m = m;
            this.n = n;
            this.board = new int[m][n];
            this.visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                System.arraycopy(picture[i], 0, board[i], 0, picture[i].length);
            }

            int group = 0;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int section = picture[i][j];
                    if (section == 0 || visited[i][j]) {
                        continue;
                    }

                    max = Math.max(max, dfs(i, j, section));
                    group += 1;
                }
            }

            return new int[]{group, max};
        }


        private int dfs(int i, int j, int target) {
            int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            int acc = 1;
            Deque<int[]> dq = new ArrayDeque<>();

            dq.push(new int[]{i, j});
            visited[i][j] = true;
            while (!dq.isEmpty()) {
                int[] pop = dq.pop();

                for (int[] elem : direction) {
                    int y = pop[0] + elem[0];
                    int x = pop[1] + elem[1];
                    if (y < 0 || x < 0 || y >= m || x >= n || visited[y][x] || board[y][x] != target) {
                        continue;
                    }
                    dq.push(new int[]{y, x});
                    visited[y][x] = true;
                    acc += 1;
                }
            }

            return acc;
        }

    }
}
