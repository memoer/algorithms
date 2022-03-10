package programmers.Lv1;

public class P12918 {
  static class Solution {
    public boolean solution(String s) {
      int l = s.length();
      if (!(l == 4 || l == 6)) {
        return false;
      }
      for (char c : s.toCharArray()) {
        if (!Character.isDigit(c)) {
          return false;
        }
      }
      return true;
    }
  }

  public static void main(String[] args) {
  }
}
