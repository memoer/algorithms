package programmers.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class G49189Other {
  static class Solution {
    private Map<Integer, List<Integer>> graph = new HashMap<>();
    private Map<Integer, Integer> path = new HashMap<>();
    private final int START_NODE = 1;
    private int max = 0;

    private void putListToGraph(int a, int b) {
      if (!graph.containsKey(a)) {
        List<Integer> list = new ArrayList<>();
        list.add(b);
        graph.put(a, list);
      } else {
        graph.get(a).add(b);
      }
    }

    private void initGraph(int[][] edge) {
      for (int[] v : edge) {
        putListToGraph(v[0], v[1]);
        putListToGraph(v[1], v[0]);
      }
    }

    private void initPath() {
      for (int node : graph.keySet()) {
        path.put(node, node == START_NODE ? 0 : Integer.MAX_VALUE);
      }
    }

    public int solution(int n, int[][] edge) {
      Queue<Integer> q = new LinkedList<>();
      initGraph(edge);
      initPath();
      q.offer(START_NODE);
      while (!q.isEmpty()) {
        int curNode = q.poll();
        int curPath = path.get(curNode);
        int adjPath = curPath + 1;
        for (int adjNode : graph.get(curNode)) {
          if (adjPath < path.get(adjNode)) {
            path.put(adjNode, adjPath);
            q.offer(adjNode);
            if (adjPath > max) {
              max = adjPath;
            }
          }
        }
      }
      return (int) path.values().stream().filter(x -> x == max).count();
    }
  }

  public static void main(String[] args) {
    int n = 6;
    int[][] edge = { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } };
    System.out.println(new Solution().solution(n, edge));
  }
}
