package programmers.highscore.dfsbfs;

import java.util.LinkedList;
import java.util.Queue;

public class P87694 {

    public static void main(String[] args) {
        int[][] rectangle = {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8},};
        int solution = new Solution().solution(rectangle, 1, 3, 7, 8);
        System.out.println(solution);
    }

    private static class Solution {

        boolean[][] map = new boolean[102][102];
        boolean[][] visited = new boolean[102][102];
        int[][] rectangle;
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        public int solution(int[][] rectangle, int cx, int cy, int ix, int iy) {
            init(rectangle);
            return bfs(cx * 2, cy * 2, ix * 2, iy * 2) / 2;
        }

        private void init(int[][] rectangle) {
            this.rectangle = rectangle;
            for (int[] v : rectangle) {
                v[0] *= 2;
                v[1] *= 2;
                v[2] *= 2;
                v[3] *= 2;
            }
            for (int[] v : rectangle) {
                for (int i = v[0]; i <= v[2]; i++) {
                    for (int j = v[1]; j <= v[3]; j++) {
                        map[i][j] = true;
                    }
                }
            }
        }

        private int bfs(int cx, int cy, int ix, int iy) {
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{cx, cy, 0});
            visited[cx][cy] = true;

            while (!q.isEmpty()) {
                int[] poll = q.poll();
                if (poll[0] == ix && poll[1] == iy) {
                    return poll[2];
                }

                for (int[] direction : directions) {
                    int nx = poll[0] + direction[0];
                    int ny = poll[1] + direction[1];
                    if (isAvailable(nx, ny)) {
                        q.offer(new int[]{nx, ny, poll[2] + 1});
                        visited[nx][ny] = true;
                    }
                }
            }
            return -1;
        }

        private boolean isAvailable(int x, int y) {
            if (visited[x][y]) {
                return false;
            } else if (!map[x][y]) {
                return false;
            }

            for (int[] v : this.rectangle) {
                boolean isIn = x > v[0] && y > v[1] && x < v[2] && y < v[3];
                if (isIn) {
                    return false;
                }
            }
            return true;
        }
    }
}