package programmers.Lv2;

import java.util.Arrays;

public class P68645_2 {

    public static void main(String[] args) {
        int[] solution = new Solution().solution(1);
        for (int i : solution) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static class Solution {

        public int[] solution(int n) {
            int[][] arr = new int[n][n];
            boolean[][] visited = new boolean[n][n];
            int DOWN = 0, RIGHT = 1, UP = 2;
            int dir = DOWN, num = 1, y = 0, x = 0;

            while (y < n && x < n && arr[y][x] == 0 ) {
                arr[y][x] = num++;
                visited[y][x] = true;

                boolean canDown = y + 1 < n && !visited[y + 1][x];
                boolean canRight = x + 1 < n && !visited[y][x + 1];
                boolean canUp = y - 1 >= 0 && x - 1 >= 0 && !visited[y - 1][x - 1];
                if (dir == DOWN) {
                    if (!canDown) {
                        dir = RIGHT;
                        x += 1;
                    } else {
                        y += 1;
                    }
                } else if (dir == RIGHT) {
                    if (!canRight) {
                        dir = UP;
                        y -= 1;
                        x -= 1;
                    } else {
                        x += 1;
                    }
                } else {
                    if (!canUp) {
                        dir = DOWN;
                        y += 1;
                    } else {
                        y -= 1;
                        x -= 1;
                    }
                }
            }
            return Arrays.stream(arr).flatMapToInt(Arrays::stream).filter(v -> v != 0).toArray();
        }

    }
}
