package programmers.level1;

public class P12916 {
  static class Solution {
    boolean solution(String s) {
      int p = 0;
      int y = 0;
      String converted = s.toLowerCase();
      for (char c : converted.toCharArray()) {
        if (c == 'p') {
          p += 1;
        } else if (c == 'y') {
          y += 1;
        }
      }
      return p == y;
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().solution("Pyy"));
  }
}
