package programmers.Lv3;

import java.util.*;

public class P49189 {
  public static void main(String[] args) {
    int n = 6;
    int[][] vertex = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
    System.out.println(new Solution().solution(n, vertex));
  }

  private static class Solution {
    private final int START_NODE = 1;
    private int[] distances;
    private Queue<Integer> q;
    private Map<Integer, List<Integer>> graph;
    private int max;

    public int solution(int n, int[][] vertex) {
      int answer = 0;
      init(n, vertex);
      while (!q.isEmpty()) {
        int node = q.poll();
        for (int adj : graph.get(node)) {
          if (distances[adj] != 0 || adj == START_NODE) continue;
          int sum = distances[node] + 1;
          distances[adj] = sum;
          q.offer(adj);
          max = Math.max(max, sum);
        }
      }
      for (int i = 2; i <= n; i++) if (distances[i] == max) answer += 1;
      return answer;
    }

    private void init(int n, int[][] vertexes) {
      max = 0;

      distances = new int[n + 1];
      distances[START_NODE] = 0;

      q = new LinkedList<>();
      q.offer(1);

      graph = new HashMap<>();
      for (int[] v : vertexes) {
        int a = v[0];
        int b = v[1];
        if (!graph.containsKey(a)) graph.put(a, new ArrayList<>());
        if (!graph.containsKey(b)) graph.put(b, new ArrayList<>());
        graph.get(a).add(b);
        graph.get(b).add(a);
      }
    }
  }
}
