package programmers.Lv1;

import java.util.Arrays;

public class P12915 {
  static class Solution {
    public String[] solution(String[] strings, int n) {
      return Arrays.stream(strings).sorted((pre, cur) -> {
        int diff = pre.charAt(n) - cur.charAt(n);
        return diff == 0 ? pre.compareTo(cur) : diff;
      }).toArray(String[]::new);
    }
  }

  public static void main(String[] args) {
    String[] strings = { "abce", "abcd", "cdx" };
    int n = 2;
    for (String s : new Solution().solution(strings, n)) {
      System.out.println(s);
    }
  }
}
