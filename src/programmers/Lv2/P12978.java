package programmers.Lv2;

import java.util.*;

public class P12978 {
  static class Solution {
    public int solution(int N, int[][] road, int K) {
      int answer = 0;
      Map<Integer, List<Node>> graph = getGraph(road);
      PriorityQueue<Node> pq = new PriorityQueue<>();
      Map<Integer, Integer> path = new HashMap<>();
      for (int i = 1; i <= N; i++) {
        if (i == 1) {
          path.put(1, 0);
        } else {
          path.put(i, Integer.MAX_VALUE);
        }
      }
      pq.offer(new Node(1, 0));
      while (!pq.isEmpty()) {
        Node cur = pq.poll();
        if (cur.weight > path.get(cur.idx)) {
          continue;
        }
        if (graph.get(cur.idx) == null) {
          continue;
        }
        for (Node adj : graph.get(cur.idx)) {
          int adjWeight = cur.weight + adj.weight;
          if (adjWeight < path.get(adj.idx)) {
            pq.offer(new Node(adj.idx, adjWeight));
            path.put(adj.idx, adjWeight);
          }
        }
      }
      for (int v : path.values()) {
        if (v <= K) {
          answer += 1;
        }
      }

      return answer;
    }

    private Map<Integer, List<Node>> getGraph(int[][] road) {
      Map<Integer, List<Node>> graph = new HashMap<>();
      for (int[] edge : road) {
        if (!graph.containsKey(edge[0])) {
          graph.put(edge[0], new ArrayList<>());
        }
        if (!graph.containsKey(edge[1])) {
          graph.put(edge[1], new ArrayList<>());
        }
        graph.get(edge[0]).add(new Node(edge[1], edge[2]));
        graph.get(edge[1]).add(new Node(edge[0], edge[2]));
      }
      return graph;
    }

    private static class Node implements Comparable<Node> {
      public int idx;
      public int weight;

      public Node(int idx, int weight) {
        this.idx = idx;
        this.weight = weight;
      }

      @Override
      public int compareTo(Node o) {
        return Integer.compare(this.weight, o.weight);
      }
    }
  }

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
    System.out.println(new Solution().solution(N, road, K));
  }

}
