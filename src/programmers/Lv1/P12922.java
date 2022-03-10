package programmers.Lv1;

public class P12922 {
  static class Solution {
    public String solution(int n) {
      final String s = "수박";
      StringBuilder sb = new StringBuilder();
      int divide = n / 2;
      for (int i = 0; i < divide; i++) {
        sb.append(s);
      }
      if (n % 2 == 1) {
        sb.append("수");
      }
      return sb.toString();
    }
  }

  public static void main(String[] args) {

  }
}
