package programmers.Lv3;

public class P42898 {
  public static void main(String[] args) {
    int x = 4;
    int y = 3;
    int[][] puddles = {{2, 2}};
    System.out.println(new Solution().solution(x, y, puddles));
  }

  private static class Solution {
    public int solution(int m, int n, int[][] puddles) {
      int[][] dp = new int[n][m];
      final int DIVIDE = 1_000_000_007;
      for (int[] puddle : puddles) {
        int x = puddle[0] - 1;
        int y = puddle[1] - 1;
        dp[y][x] = -1;
      }
      for (int i = 1; i < m && dp[0][i] != -1; i++) dp[0][i] = 1;
      for (int i = 1; i < n && dp[i][0] != -1; i++) dp[i][0] = 1;

      for (int i = 1; i < n; i++) {
        for (int j = 1; j < m; j++) {
          if (dp[i][j] == -1) {
            continue;
          } else if (dp[i - 1][j] == -1 && dp[i][j - 1] == -1) {
            continue;
          } else if (dp[i][j - 1] == -1) {
            dp[i][j] = dp[i - 1][j];
          } else if (dp[i - 1][j] == -1) {
            dp[i][j] = dp[i][j - 1];
          } else {
            dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % DIVIDE;
          }
        }
      }
      return dp[n - 1][m - 1];
    }
  }
}
