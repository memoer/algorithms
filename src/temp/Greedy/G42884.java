package temp.Greedy;

import java.util.Arrays;

public class G42884 {
  public static void main(String[] args) {
    int[][] a = { { 0, 1 }, { 2, 3 } };
    int[][] b = { { -2, -1 }, { 1, 2 }, { -3, 0 } }; // 2
    int[][] c = { { 0, 0 } }; // 1
    int[][] d = { { 0, 1 }, { 0, 1 }, { 1, 2 } }; // 1
    int[][] e = { { 0, 1 }, { 2, 3 }, { 4, 5 }, { 6, 7 } }; // 4
    int[][] f = { { -20, -15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } }; // 2
    int[][] g = { { -20, 15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } }; // 2
    int[][] h = { { -20, 15 }, { -20, -15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } }; // 2
    System.out.println(new Solution().solution(b));
  }

  // -2,-1 / -3,0 / 1,2
  static class Solution {
    public int solution(int[][] routes) {
      int result = routes.length;
      Arrays.sort(routes, (pre, cur) -> pre[1] - cur[1]);
      int target = routes[0][1];
      for (int i = 1; i < routes.length; i++) {
        int start = routes[i][0];
        int end = routes[i][1];
        if (target >= start) {
          result--;
        } else {
          target = end;
        }
      }
      return result;
    }
  }
}
