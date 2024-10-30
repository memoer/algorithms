package programmers.highscore.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class P49189 {

    public static void main(String[] args) {
        int n = 6;
        int[][] vertex = {
            {3, 6},
            {4, 3},
            {3, 2},
            {1, 3},
            {1, 2},
            {2, 4},
            {5, 2}
        };
        int solution = new Solution().solution(n, vertex);
        System.out.println(solution);
    }

    private static class Solution {

        private int[] distance;
        final int START_NODE = 1;
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();

        public int solution(int n, int[][] vertex) {
            int max = Integer.MIN_VALUE;

            init(n, vertex);

            while (!q.isEmpty()) {
                Integer node = q.poll();
                for (Integer adj : graph.get(node)) {
                    int weight = distance[node] + 1;
                    if (distance[adj] <= weight) {
                        continue;
                    }
                    distance[adj] = weight;
                    q.offer(adj);
                    max = Math.max(max, weight);
                }
            }

            return answer(max);
        }

        private int answer(int max) {
            int count = 0;
            for (int i : distance) {
                if (max == i) {
                    count += 1;
                }
            }
            return count;
        }

        private void init(int n, int[][] vertex) {
            this.distance = new int[n + 1];
            this.q = new LinkedList<>();
            this.graph = new HashMap<>();

            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[START_NODE] = 0;
            q.offer(START_NODE);
            for (int[] edge : vertex) {
                int a = edge[0];
                int b = edge[1];
                if (!graph.containsKey(a)) {
                    graph.put(a, new ArrayList<>());
                }
                if (!graph.containsKey(b)) {
                    graph.put(b, new ArrayList<>());
                }
                graph.get(a).add(b);
                graph.get(b).add(a);
            }
        }

    }
}
