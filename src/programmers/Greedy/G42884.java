package programmers.Greedy;

import java.util.Arrays;

public class G42884 {
  static class Solution {
    public int solution(int[][] routes) {
      int curIdx = 0;
      int answer = 0;
      Arrays.sort(routes, (pre, cur) -> Integer.compare(pre[0], cur[0]));
      while (curIdx < routes.length) {
        int curB = routes[curIdx++][1];
        for (; curIdx < routes.length; ++curIdx) {
          int nextA = routes[curIdx][0];
          int nextB = routes[curIdx][1];
          if (curB >= nextA) {
            curB = Math.min(curB, nextB);
          } else {
            break;
          }
        }
        answer++;
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
    System.out.println(new Solution().solution(h));
  }
}
