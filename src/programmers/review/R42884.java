package programmers.review;

import java.util.Arrays;

public class R42884 {
  static class Solution {
    public int solution(int[][] routes) {
      int answer = routes.length;
      int idx = 0;
      Arrays.sort(routes, (pre, cur) -> pre[1] - cur[1]);
      for (int i = idx + 1; i < routes.length; i++) {
        int curEnd = routes[idx][1];
        int nextStart = routes[i][0];
        if (curEnd >= nextStart) {
          answer--;
        } else {
          idx = i;
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[][] b = { { -2, -1 }, { 1, 2 }, { -3, 0 } }; // 2
    int[][] c = { { 0, 0 } }; // 1
    int[][] d = { { 0, 1 }, { 0, 1 }, { 1, 2 } }; // 1
    int[][] e = { { 0, 1 }, { 2, 3 }, { 4, 5 }, { 6, 7 } }; // 4
    int[][] f = { { -20, -15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } }; // 2
    int[][] g = { { -20, 15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } }; // 2
    int[][] h = { { -20, 15 }, { -20, -15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } }; // 2
    int[][][] test = { b, c, d, e, f, g, h };
    for (int[][] r : test) {
      System.out.println(new Solution().solution(r));
    }
  }
}

// -20,-15 -> -18,-13 -> -14,-5 -> -5,-3