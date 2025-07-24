package programmers.highscore.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P49191 {

    public static void main(String[] args) {
        int n = 5;
        int[][] results = {
            {4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}
        };
        int solution = new Solution().solution(n, results);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int n, int[][] results) {
            List<Integer>[] wins = new List[n + 1];
            List<Integer>[] loses = new List[n + 1];
            for (int i = 1; i <= n; i++) {
                wins[i] = new ArrayList<>();
                loses[i] = new ArrayList<>();
            }
            for (int[] result : results) {
                int win = result[0];
                int lose = result[1];
                wins[win].add(lose);
                loses[lose].add(win);
            }

            int answer = 0;
            for (int i = 1; i <= n; i++) {
                boolean[] visited = new boolean[n + 1];
                Queue<Integer> q = new LinkedList<>();

                bfs(q, i, visited, wins);
                bfs(q, i, visited, loses);
                if (check(n, visited)) {
                    answer += 1;
                }
            }
            return answer;
        }

        private void bfs(Queue<Integer> q, int i, boolean[] visited, List<Integer>[] list) {
            q.offer(i);
            visited[i] = true;
            while (!q.isEmpty()) {
                Integer a = q.poll();
                for (Integer b : list[a]) {
                    if (visited[b]) {
                        continue;
                    }
                    visited[b] = true;
                    q.offer(b);
                }
            }
        }

        private boolean check(int n, boolean[] visited) {
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
