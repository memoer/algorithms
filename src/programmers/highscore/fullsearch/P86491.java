package programmers.highscore.fullsearch;

import java.util.Arrays;

public class P86491 {

  public static void main(String[] args) {
    int[][] sizes = new int[][]{
        {60, 50},
        {30, 70},
        {60, 30},
        {80, 40}
    };
    int solution = new Solution().solution(sizes);
    System.out.println(solution);
  }

  private static class Solution {

    public int solution(int[][] sizes) {
      int[] ints = Arrays.stream(sizes)
          .reduce((pre, cur) -> new int[]{
              Math.max(Math.max(pre[0], pre[1]), Math.max(cur[0], cur[1])),
              Math.max(Math.min(pre[0], pre[1]), Math.min(cur[0], cur[1]))
          })
          .get();
      return ints[0] * ints[1];
    }

  }
}