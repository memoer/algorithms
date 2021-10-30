package structureNPattern.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kruskal {

  static class Solution {
    private static final int[][] edgeList = { { 7, 'A', 'B' }, { 5, 'A', 'D' }, { 8, 'B', 'C' }, { 9, 'B', 'D' },
        { 7, 'B', 'E' }, { 5, 'C', 'E' }, { 8, 'E', 'F' }, { 9, 'E', 'G' }, { 7, 'D', 'E' }, { 6, 'D', 'F' },
        { 11, 'F', 'G' }, };
    private Map<Character, Integer> rank = new HashMap<>();
    private Map<Character, Character> parent = new HashMap<>();

    private void init() {
      Arrays.sort(edgeList, (pre, cur) -> pre[0] - cur[0]);
      for (int[] edge : edgeList) {
        char a = (char) edge[1];
        char b = (char) edge[2];
        parent.put(a, a);
        rank.put(a, 1);
        parent.put(b, b);
        rank.put(b, 1);
      }
    }

    private void union(char rootA, char rootB) {
      int rankA = rank.get(rootA);
      int rankB = rank.get(rootB);
      if (rankA > rankB) {
        parent.put(rootB, rootA);
      } else {
        parent.put(rootA, rootB);
        if (rankA == rankB) {
          // union-by-rank
          rank.put(rootB, rankB + 1);
        }
      }
    }

    private char find(char node) {
      if (node != parent.get(node)) {
        // path compression
        parent.put(node, find(parent.get((node))));
      }
      return parent.get(node);
    }

    public List<int[]> solution() {
      List<int[]> answer = new ArrayList<>();
      init();
      for (int[] edge : edgeList) {
        char rootA = find((char) edge[1]);
        char rootB = find((char) edge[2]);
        if (rootA != rootB) {
          union(rootA, rootB);
          answer.add(edge);
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    for (int[] edge : new Solution().solution()) {
      System.out.println(edge[0] + ", " + (char) edge[1] + ", " + (char) edge[2]);
    }
  }
}
