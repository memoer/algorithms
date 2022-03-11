package temp.Greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class G42861Kruskal {
  public static void main(String[] args) {
    int n = 4;
    int[][] costs = { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } };
    System.out.println(new Solution().solution(n, costs));
  }

  static class Solution {
    private Map<Integer, Integer> parent = new HashMap<>();
    private Map<Integer, Integer> rank = new HashMap<>();

    private void initialize(int[][] costs) {
      Arrays.sort(costs, (pre, cur) -> pre[2] - cur[2]);
      for (int[] cost : costs) {
        int a = cost[0];
        int b = cost[1];
        parent.put(a, a);
        parent.put(b, b);
        rank.put(a, 1);
        rank.put(b, 1);
      }
    }

    private int find(int node) {
      if (node != parent.get(node)) {
        parent.put(node, find(parent.get(node)));
      }
      return parent.get(node);
    }

    private void union(int nodeA, int nodeB) {
      int rootA = find(nodeA);
      int rootB = find(nodeB);
      if (rootA == rootB) {
        return;
      }
      if (rank.get(rootA) > rank.get(rootB)) {
        parent.put(rootB, rootA);
      } else {
        parent.put(rootA, rootB);
        if (rank.get(rootA) == rank.get(rootB)) {
          rank.put(rootB, rank.get(rootB) + 1);
        }
      }
    }

    public int solution(int n, int[][] costs) {
      int result = 0;
      initialize(costs);

      for (int[] cost : costs) {
        int nodeA = cost[0];
        int nodeB = cost[1];
        int weight = cost[2];
        if (find(nodeA) != find(nodeB)) {
          union(nodeA, nodeB);
          result += weight;
        }
      }
      return result;
    }
  }

}
