package programmers.Greedy;

import java.util.Arrays;
import java.util.HashMap;

public class G42861 {
  static class Solution {
    HashMap<Integer, Integer> paranet = new HashMap<>();
    HashMap<Integer, Integer> rank = new HashMap<>();

    private void init(int[][] costs) {
      // 크루스칼 알고리즘은 가중치가 가장 작은 간선부터 시작한다.
      Arrays.sort(costs, (pre, cur) -> pre[2] - cur[2]);
      for (int[] cost : costs) {
        int start = cost[0];
        int end = cost[1];
        paranet.put(start, start);
        paranet.put(end, end);
        rank.put(start, 1);
        rank.put(end, 1);
      }
    }

    private int find(int node) {
      if (paranet.get(node) != node) {
        paranet.put(node, find(paranet.get(node)));
      }
      return paranet.get(node);
    }

    private void union(int nodeA, int nodeB) {
      int rootA = find(nodeA);
      int rootB = find(nodeB);
      int rankA = rank.get(rootA);
      int rankB = rank.get(rootB);
      if (rankA > rankB) {
        paranet.put(rootB, rootA);
      } else {
        paranet.put(rootA, rootB);
        if (rankA == rankB) {
          rank.put(rootB, rankB + 1);
        }
      }
    }

    public int solution(int n, int[][] costs) {
      int answer = 0;
      init(costs);
      for (int[] cost : costs) {
        int nodeA = cost[0];
        int nodeB = cost[1];
        if (find(nodeA) != find(nodeB)) {
          union(nodeA, nodeB);
          answer += cost[2];
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int n = 4;
    // start, end, weight
    int[][] costs = { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } };
    System.out.println(new Solution().solution(n, costs));
  }
}
