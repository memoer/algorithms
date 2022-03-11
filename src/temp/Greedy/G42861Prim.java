package temp.Greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class G42861Prim {
  public static void main(String[] args) {
    int n = 4;
    int[][] costs = { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } };
    System.out.println(new Solution().solution(n, costs));
  }

  static class Solution {
    class Edge implements Comparable<Edge> {
      int me;
      int adj;
      int weight;

      public Edge(int me, int adj, int weight) {
        this.me = me;
        this.adj = adj;
        this.weight = weight;
      }

      @Override
      public int compareTo(Edge o) {
        return this.weight - o.weight;
      }
    }

    public static final int START_NODE = 0;
    private Map<Integer, List<Edge>> map;

    private void initialize(int[][] costs, Set<Integer> connected, PriorityQueue<Edge> pq) {
      map = new HashMap<>();
      for (int[] cost : costs) {
        int me = cost[0];
        int adj = cost[1];
        int weight = cost[2];
        if (!map.containsKey(me)) {
          map.put(me, new ArrayList<>());
        }
        if (!map.containsKey(adj)) {
          map.put(adj, new ArrayList<>());
        }
        map.get(me).add(new Edge(me, adj, weight));
        map.get(adj).add(new Edge(adj, me, weight));
      }
      connected.add(START_NODE);
      for (Edge edge : map.get(START_NODE)) {
        pq.add(edge);
      }
    }

    public int solution(int n, int[][] costs) {
      int result = 0;
      Set<Integer> connected = new HashSet<>();
      PriorityQueue<Edge> pq = new PriorityQueue<>();

      initialize(costs, connected, pq);

      while (!pq.isEmpty()) {
        Edge polledEdge = pq.poll();
        if (connected.contains(polledEdge.adj)) {
          continue;
        }
        result += polledEdge.weight;
        connected.add(polledEdge.adj);
        for (Edge edge : map.get(polledEdge.adj)) {
          pq.add(edge);
        }
      }
      return result;
    }
  }

}
