package temp.Lv1;

import java.util.Arrays;

public class P86491 {
  static class Solution {
    public int solution(int[][] sizes) {
      return Arrays.stream(sizes)
          .reduce((pre, cur) -> new int[] { Math.max(Math.min(pre[0], pre[1]), Math.min(cur[0], cur[1])),
              Math.max(Math.max(pre[0], pre[1]), Math.max(cur[0], cur[1])) })
          .map(m -> m[0] * m[1]).get();
    }
  }

  public static void main(String[] args) {
    int[][] sizes = {
        { 60, 50 },
        { 30, 70 },
        { 60, 30 },
        { 80, 40 },
    };
    System.out.println(new Solution().solution(sizes));
  }
}
