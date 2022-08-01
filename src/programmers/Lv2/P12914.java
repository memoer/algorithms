package programmers.Lv2;

public class P12914 {
  public static void main(String[] args) {
    int n = 4;
    System.out.println(new Solution().solution(n));
  }

  private static class Solution {

    public long solution(int n) {
      if (n == 1) return 1;
      long[] dp = new long[n + 1];
      dp[1] = 1L;
      dp[2] = 2L;
      for (int i = 3; i < n + 1; i++) dp[i] = (dp[i - 2] + dp[i - 1]) % 1_234_567;
      return dp[n];
    }
  }
}
