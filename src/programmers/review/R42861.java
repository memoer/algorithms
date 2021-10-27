package programmers.review;

import java.util.Arrays;
import java.util.HashMap;

public class R42861 {
  static class Solution {
    private HashMap<Integer, Integer> parent = new HashMap<>();
    private HashMap<Integer, Integer> rank = new HashMap<>();

    private void init(int[][] costs) {
      // 간선 가중치 오름차순 정렬 -> 크루스칼 알고리즘
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

    private void union(int rootA, int rootB) {
      int rankA = rank.get(rootA);
      int rankB = rank.get(rootB);
      if (rankA > rankB) {
        parent.put(rootB, rootA);
      } else {
        parent.put(rootA, rootB);
        if (rankA == rankB) {
          rank.put(rootB, rankB + 1);
        }
      }
    }

    private int find(int node) {
      if (node != parent.get(node)) {
        // path compression
        parent.put(node, find(parent.get(node)));
      }
      return parent.get(node);
    }

    public int solution(int n, int[][] costs) {
      int answer = 0;
      init(costs);
      for (int[] cost : costs) {
        int rootA = find(cost[0]);
        int rootB = find(cost[1]);
        if (rootA != rootB) {
          union(rootA, rootB);
          answer += cost[2];
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int n = 4;
    int[][] costs = { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } };
    System.out.println(new Solution().solution(n, costs));
  }
}
