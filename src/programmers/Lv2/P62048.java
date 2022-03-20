package programmers.Lv2;

public class P62048 {
  static class Solution {
    private long gcd(int w, int h) {
      long a = w, b = h, c = 0L;
      while (b != 0) {
        c = a % b;
        a = b;
        b = c;
      }
      return a;
    }

    public long solution(int w, int h) {
      return (w * h) - (long) Math.pow(gcd(w, h), 2);
    }
  }

  public static void main(String[] args) {
    new Solution().solution(8, 12);
  }

}
