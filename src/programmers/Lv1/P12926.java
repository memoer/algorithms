package programmers.Lv1;

public class P12926 {
  static class Solution {
    public String solution(String s, int n) {
      StringBuilder sb = new StringBuilder();
      n %= 26;
      for (char ch : s.toCharArray()) {
        if (Character.isLowerCase(ch)) {
          ch = (char) ((ch + n - 97) % 26 + 97);
        } else if (Character.isUpperCase(ch)) {
          ch = (char) ((ch + n - 65) % 26 + 65);
        }
        sb.append(ch);
      }
      return sb.toString();
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().solution("a B z", 4));
  }
}
