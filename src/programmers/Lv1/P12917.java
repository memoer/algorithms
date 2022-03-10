package programmers.Lv1;

import java.util.Arrays;

public class P12917 {
  static class Solution {
    public String solution(String s) {
      char[] cArr = s.toCharArray();
      Arrays.sort(cArr);
      return new StringBuilder(String.valueOf(cArr)).reverse().toString();
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().solution("Zbcdefg"));
  }
}
