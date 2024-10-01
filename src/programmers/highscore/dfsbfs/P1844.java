package programmers.highscore.dfsbfs;

import java.util.LinkedList;
import java.util.Queue;

public class P1844 {

    public static void main(String[] args) {
        int[][] maps = {
            {1, 0, 1, 1, 1},
            {1, 0, 1, 0, 1},
            {1, 0, 1, 1, 1},
            {1, 1, 1, 0, 1},
            {0, 0, 0, 0, 1}
        };
        int solution = new Solution().solution(maps);
        System.out.println(solution);
    }

    private static class Solution {

        private int height;
        private int width;

        public int solution(int[][] maps) {
            height = maps.length;
            width = maps[0].length;
            boolean[][] visited = new boolean[height][width];
            int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
            int[] dest = new int[]{height - 1, width - 1};
            Queue<int[]> q = new LinkedList<>();

            q.offer(new int[]{0, 0, 1});
            visited[0][0] = true;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                if (cur[0] == dest[0] && cur[1] == dest[1]) {
                    return cur[2];
                }

                for (int[] direction : directions) {
                    int[] next = {cur[0] + direction[0], cur[1] + direction[1], cur[2] + 1};
                    if (isAvailable(next, visited, maps)) {
                        visited[next[0]][next[1]] = true;
                        q.offer(next);
                    }
                }
            }

            return -1;
        }

        private boolean isAvailable(int[] arr, boolean[][] visited, int[][] maps) {
            int i = arr[0];
            int j = arr[1];
            return i >= 0 && j >= 0 && i < height && j < width && !visited[i][j] && maps[i][j] != 0;
        }
    }
}
