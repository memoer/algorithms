package programmers.highscore.fullsearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P87946 {

  public static void main(String[] args) {
    int k = 80;
    int[][] dungeons = new int[][]{
        {80, 20},
        {50, 40},
        {30, 10},
    };
    int solution = new Solution().solution(k, dungeons);
    System.out.println(solution);
  }

  private static class Solution {

    Set<int[]> set = new HashSet<>();
    int len;

    public int solution(int k, int[][] dungeons) {
      initialize(dungeons);
      return getAnswer(k, dungeons);
    }

    private void initialize(int[][] dungeons) {
      len = dungeons.length;
      int[] orders = new int[len];
      boolean[] visited = new boolean[len];
      permutation(orders, visited, 0);
    }

    private void permutation(int[] orders, boolean[] visited, int depth) {
      if (depth >= len) {
        set.add(Arrays.copyOfRange(orders, 0, len));
        return;
      }
      for (int i = 0; i < len; i++) {
        if (visited[i]) {
          continue;
        }
        orders[depth] = i;
        visited[i] = true;
        permutation(orders, visited, depth + 1);
        visited[i] = false;
      }
    }

    private int getAnswer(int k, int[][] dungeons) {
      int answer = 0;
      for (int[] orders : set) {
        int n = 0;
        int user = k;
        for (int order : orders) {
          int[] dungeon = dungeons[order];
          int required = dungeon[0];
          if (required > user) {
            continue;
          }
          user -= dungeon[1];
          n += 1;
        }
        answer = Math.max(answer, n);
      }
      return answer;
    }
  }
}
