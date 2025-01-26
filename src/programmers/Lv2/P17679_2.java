package programmers.Lv2;

import java.util.ArrayDeque;
import java.util.Queue;

public class P17679_2 {

    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        String[] board = {
            "CCBDE", "AAADE", "AAABF", "CCBBF"
        };
        int solution = new Solution().solution(m, n, board);
        System.out.println(solution);
    }

    private static class Solution {

        private final char EMPTY = '\0';
        int[][] square = {{0, 0}, {1, 0}, {0, 1}, {1, 1}};
        private int m;
        private int n;
        private char[][] board;


        public int solution(int m, int n, String[] board) {
            this.m = m;
            this.n = n;
            this.board = new char[m][n];
            for (int i = 0; i < m; i++) {
                this.board[i] = board[i].toCharArray();
            }

            int answer = 0;
            while (true) {
                boolean[][] checked = check();
                int count = delete(checked);
                if (count == 0) {
                    break;
                }

                answer += count;
                down();
            }

            return answer;
        }

        private boolean[][] check() {
            boolean[][] chekced = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == EMPTY) {
                        continue;
                    }

                    int[][] target = find(i, j);
                    if (target == null) {
                        continue;
                    }

                    for (int k = 0; k < 4; k++) {
                        chekced[target[k][0]][target[k][1]] = true;
                    }
                }
            }
            return chekced;
        }

        private int[][] find(int i, int j) {
            int[][] arr = new int[4][2];
            char target = this.board[i][j];
            for (int k = 0; k < 4; k++) {
                int y = i + square[k][0];
                int x = j + square[k][1];

                boolean isValid1 = y >= 0 && y < m && x >= 0 && x < n;
                if (!isValid1) {
                    return null;
                }

                boolean isValid2 = board[y][x] == target;
                if (!isValid2) {
                    return null;
                }

                arr[k] = new int[]{y, x};
            }
            return arr;
        }

        private int delete(boolean[][] chekced) {
            int count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!chekced[i][j]) {
                        continue;
                    }

                    count += 1;
                    this.board[i][j] = EMPTY;
                }
            }
            return count;
        }

        private void down() {
            for (int j = 0; j < n; j++) {
                Queue<Integer> q = new ArrayDeque<>();
                for (int i = m - 1; i >= 0; i--) {
                    if (this.board[i][j] == EMPTY) {
                        q.offer(i);
                    } else if (!q.isEmpty()) {
                        char temp = this.board[i][j];
                        this.board[i][j] = EMPTY;
                        this.board[q.poll()][j] = temp;
                        q.offer(i);
                    }
                }
            }
        }

    }
}
