package programmers.Lv2;

import java.util.Arrays;

public class P12939 {
  public static void main(String[] args) {
    String s = "1 2 3 4";
    System.out.println(new Solution().solution(s));
  }

  private static class Solution {
    public String solution(String s) {
      int[] array = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
      return array[0] + " " + array[array.length - 1];
    }
  }
}
