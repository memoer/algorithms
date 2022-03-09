package programmers.level1;

public class P12903 {
  static class Solution {
    public String solution(String s) {
      return s.substring((s.length() - 1) / 2, s.length() / 2 + 1);
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().solution("qwer"));
  }
}
