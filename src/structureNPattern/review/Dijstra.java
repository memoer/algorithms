package structureNPattern.review;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijstra {
  static class Solution {
    private Map<Character, int[][]> graph = new HashMap<>();
    private Map<Character, Integer> path = new HashMap<>();

    private void init(char startNode, PriorityQueue<Character> pq) {
      graph.put('A', new int[][] { { 'B', 8 }, { 'C', 1 }, { 'D', 2 } });
      graph.put('B', new int[][] {});
      graph.put('C', new int[][] { { 'B', 5 }, { 'D', 2 } });
      graph.put('D', new int[][] { { 'E', 3 }, { 'F', 5 } });
      graph.put('E', new int[][] { { 'F', 1 } });
      graph.put('F', new int[][] { { 'A', 5 } });
      for (char key : graph.keySet()) {
        path.put(key, key == startNode ? 0 : Integer.MAX_VALUE);
      }
      pq.offer(startNode);
    }

    public Map<Character, Integer> solution(char startNode) {
      PriorityQueue<Character> pq = new PriorityQueue<>();
      init(startNode, pq);
      while (!pq.isEmpty()) {
        char node = pq.poll();
        for (int[] edge : graph.get(node)) {
          char adj = (char) edge[0];
          int weight = edge[1];
          int sumWeight = path.get(node) + weight;
          if (sumWeight < path.get(adj)) {
            pq.offer(adj);
            path.put(adj, sumWeight);
          }
        }
      }
      return path;
    }
  }

  public static void main(String[] args) {
    Map<Character, Integer> answer = new Solution().solution('A');
    for (char key : answer.keySet()) {
      System.out.println(key + " : " + answer.get(key));
    }
  }
}
