package programmers.highscore.greedy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class P42861 {

    public static void main(String[] args) {
        int n = 4;
        int[][] costs = new int[][]{
            {0, 1, 1},
            {0, 2, 2},
            {1, 2, 5},
            {1, 3, 1},
            {2, 3, 8}
        };
        int answer = new Solution().solution(4, costs);
        System.out.println(answer);
    }

    private static class Solution {

        public int solution(int n, int[][] costs) {
            List<int[]> edges = createEdges(costs);

            int answer = 0;
            Set<Integer> connected = new HashSet<>();
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(weight(o1), weight(o2)));

            int START_NODE = 0;
            connected.add(START_NODE);
            for (int[] edge : edges) {
                if (me(edge) == START_NODE) {
                    pq.offer(edge);
                }
            }

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                if (connected.contains(opposite(cur))) {
                    continue;
                }
                answer += weight(cur);
                connected.add(opposite(cur));
                for (int[] edge : edges) {
                    if (opposite(cur) != me(edge) || connected.contains(opposite(edge))) {
                        continue;
                    }
                    pq.offer(edge);
                }
            }

            return answer;
        }

        private List<int[]> createEdges(int[][] costs) {
            List<int[]> list = new ArrayList<>();
            int len = costs.length;
            for (int i = 0; i < len; i++) {
                int[] cost = costs[i];
                list.add(new int[]{me(cost), opposite(cost), weight(cost)});
                list.add(new int[]{opposite(cost), me(cost), weight(cost)});
            }
            return list;
        }

        private int me(int[] arr) {
            return arr[0];
        }

        private int opposite(int[] arr) {
            return arr[1];
        }

        private int weight(int[] arr) {
            return arr[2];
        }
    }
}
