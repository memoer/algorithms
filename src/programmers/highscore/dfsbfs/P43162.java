package programmers.highscore.dfsbfs;

import java.util.LinkedList;
import java.util.Queue;

public class P43162 {

    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {
            {1, 1, 0},
            {1, 1, 0},
            {0, 0, 1}
        };
        int solution = new Solution().solution(n, computers);
        System.out.println(solution);
    }

    private static class Solution {

        private int[][] computers;
        private boolean[] visited;

        public int solution(int n, int[][] computers) {
            this.visited = new boolean[n];
            this.computers = computers;
            int answer = 0;

            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    continue;
                }
                connect(n, i);
                answer += 1;
            }

            return answer;
        }

        private void connect(int n, int i) {
            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            visited[i] = true;
            while (!q.isEmpty()) {
                Integer poll = q.poll();
                for (int j = 0; j < n; j++) {
                    int v = computers[poll][j];
                    if (v == 0 || visited[j]) {
                        continue;
                    }
                    q.offer(j);
                    visited[j] = true;
                }
            }
        }

    }
}
