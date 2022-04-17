package programmers.Lv2;

public class P1844 {

    static class Solution {
        final int[][] NEXT_CANDIDATE = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        private int[][] maps;
        private boolean[][] visited;
        private int lengthY;
        private int lengthX;
        private int answer;

        private boolean canPass(int nextY, int nextX) {
            return nextY < lengthY && nextY >= 0 && nextX < lengthX && nextX >= 0 && !this.visited[nextY][nextX] && maps[nextY][nextX] != 0;
        }

        private void dfs(int y, int x, int acc) {
            if (y == lengthY - 1 && x == lengthX - 1) {
                this.answer = Math.min(this.answer, acc);
                return;
            }
            for (int[] next : this.NEXT_CANDIDATE) {
                int nextY = y + next[0];
                int nextX = x + next[1];
                if (this.canPass(nextY, nextX)) {
                    this.visited[nextY][nextX] = true;
                    dfs(nextY, nextX, acc + 1);
                    this.visited[nextY][nextX] = false;
                }
            }
        }

        public int solution(int[][] maps) {
            this.answer = Integer.MAX_VALUE;
            this.maps = maps;
            this.lengthY = maps.length;
            this.lengthX = maps[0].length;
            this.visited = new boolean[lengthY][lengthX];
            for (int i = 0; i < lengthY; i++) {
                for (int j = 0; j < lengthX; j++) {
                    this.visited[i][j] = maps[i][j] == 0;
                }
            }
            dfs(0, 0, 1);
            return this.answer == Integer.MAX_VALUE ? -1 : this.answer;
        }
    }

    public static void main(String[] args) {
        int[][] maps = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1}
        };
        System.out.println(new Solution().solution(maps));
    }
}
