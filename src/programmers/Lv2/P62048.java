package programmers.Lv2;

public class P62048 {
  static class Solution {
    private long gcd(long w, long h) {
      long c = 0L;
      while (h != 0) {
        c = w % h;
        w = h;
        h = c;
      }
      return w;
    }

    public long solution(int w, int h) {
      long a = w, b = h;
      return (long) (a * b) - (a + b - gcd(a, b));
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().solution(8, 2));
  }

}
