package programmers.Lv2;

public class P12945 {
  public static void main(String[] args) {
    System.out.println(new Solution().solution(100_000));
  }

  static class Solution {
    public int solution(int n) {
      if (n == 0 || n == 1 || n == 2) return n;
      int end = n - 2;
      int tmp;
      int a = 1;
      int b = 1;
      for (int i = 0; i < end; i++) {
        tmp = b;
        b = (a + b) % 1234567;
        a = tmp;
      }
      return b;
    }
  }
}
