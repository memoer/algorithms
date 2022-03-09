package programmers.level1;

public class P12912 {
  static class Solution {
    private long getResult(long min, long max) {
      return (min + max + 1) * (max - min) / 2;
    }

    public long solution(int a, int b) {
      return getResult(Math.min(a, b) - 1, Math.max(a, b));
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().solution(3, 5));
  }
}
