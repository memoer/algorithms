package programmers.Lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class P12978_2 {

    public static void main(String[] args) {
        int N = 5;
        int[][] road = {
            {1, 2, 1},
            {2, 3, 3},
            {5, 2, 2},
            {1, 4, 2},
            {5, 3, 1},
            {5, 4, 2}
        };
        int K = 3;
        int solution = new Solution().solution(N, road, K);
        System.out.print(solution);
    }

    private static class Solution {

        public int solution(int N, int[][] road, int K) {
            List<int[]>[] graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int[] edge : road) {
                graph[edge[0]].add(new int[]{edge[1], edge[2]});
                graph[edge[1]].add(new int[]{edge[0], edge[2]});
            }
            int[] distance = new int[N + 1];
            int START_NODE = 1;
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[START_NODE] = 0;

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
            pq.offer(new int[]{START_NODE, 0});
            while (!pq.isEmpty()) {
                int[] poll = pq.poll();
                for (int[] adj : graph[poll[0]]) {
                    int weight = poll[1] + adj[1];
                    if (weight >= distance[adj[0]]) {
                        continue;
                    }

                    pq.offer(new int[]{adj[0], weight});
                    distance[adj[0]] = weight;
                }
            }

            return (int) Arrays.stream(distance).filter(v -> v <= K).count();
        }

    }
}
