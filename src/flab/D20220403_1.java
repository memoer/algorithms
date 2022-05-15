package flab;

import java.util.*;

public class D20220403_1 {
  public static void main(String[] args) {
    int n = 6;
    int[][] edges = {{0,1},{0,2},{3,5},{5,4},{4,3}};
    int src = 0;
    int dest = 5;
    System.out.println(new Solution().validPath(n, edges, src, dest));
  }

  static class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
      if(source == destination) return true;
      Map<Integer, List<Integer>> graph = getGraph(edges);
      boolean[] visited = new boolean[n];
      Queue<Integer> q = new LinkedList<>();

      q.offer(source);
      visited[source] = true;
      while (!q.isEmpty()) {
        int cur = q.poll();
        for (int adj : graph.get(cur)) {
          if (adj == destination) return true;
          else if (visited[adj]) continue;
          q.offer(adj);
          visited[adj] = true;
        }
      }
      return false;
    }

    private Map<Integer, List<Integer>> getGraph(int[][] edges) {
      Map<Integer, List<Integer>> graph = new HashMap<>();
      for (int[] edge : edges) {
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
      return graph;
    }
  }
}
