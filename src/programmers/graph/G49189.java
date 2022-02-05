package programmers.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class G49189 {
  public static void main(String[] args) {
    int n = 6;
    int[][] edge = { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } };
    System.out.println(new Solution().solution(n, edge));
  }

  static class Solution {
    public final int START_NODE = 1;
    private Queue<Integer> q;
    private Map<Integer, Queue<Integer>> graph;
    private int[] dist;
    private int max;

    private void initialize(int n, int[][] vertex) {
      this.q = new LinkedList<>();
      q.add(this.START_NODE);

      this.graph = new HashMap<>();
      for (int[] edge : vertex) {
        int nodeA = edge[0];
        int nodeB = edge[1];
        if (!graph.containsKey(nodeA)) {
          graph.put(nodeA, new LinkedList<>());
        }
        if (!graph.containsKey(nodeB)) {
          graph.put(nodeB, new LinkedList<>());
        }
        graph.get(nodeA).offer(nodeB);
        graph.get(nodeB).offer(nodeA);
      }

      final int START_NODE_DISTANCE = 1;
      this.dist = new int[n + 1];
      this.dist[this.START_NODE] = START_NODE_DISTANCE;
      this.max = START_NODE_DISTANCE;
    }

    private void bfs() {
      while (!this.q.isEmpty()) {
        int curNode = q.poll();
        while (!this.graph.get(curNode).isEmpty()) {
          int adjNode = this.graph.get(curNode).poll();
          if (this.dist[adjNode] == 0) {
            this.dist[adjNode] += this.dist[curNode] + 1;
            this.q.offer(adjNode);
          }
        }
        this.max = this.dist[curNode];
      }
    }

    private int getResult(int n) {
      int result = 0;
      for (int num : this.dist) {
        if (num == this.max) {
          result++;
        }
      }
      return result;
    }

    public int solution(int n, int[][] vertex) {
      initialize(n, vertex);
      bfs();
      return getResult(n);
    }
  }
}
