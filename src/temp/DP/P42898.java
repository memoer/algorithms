package temp.DP;

public class P42898 {
  public static void main(String[] args) {
    int m = 4, n = 3;
    int[][] puddles = { { 2, 2 } };
    System.out.println(new Solution().solution(m, n, puddles));
  }

  static class Solution {
    public int solution(int m, int n, int[][] puddles) {
      int[][] grid = new int[n][m];
      for (int[] puddle : puddles) {
        int x = puddle[0];
        int y = puddle[1];
        grid[y - 1][x - 1] = -1;
      }
      for (int i = 0; i < m; i++) {
        if (grid[0][i] == -1) {
          break;
        }
        grid[0][i] = 1;
      }
      for (int i = 0; i < n; i++) {
        if (grid[i][0] == -1) {
          break;
        }
        grid[i][0] = 1;
      }
      for (int i = 1; i < n; i++) {
        for (int j = 1; j < m; j++) {
          if (grid[i][j] == -1) {
            continue;
          }
          int top = grid[i - 1][j] == -1 ? 0 : grid[i - 1][j] % 1_000_000_007;
          int left = grid[i][j - 1] == -1 ? 0 : grid[i][j - 1] % 1_000_000_007;
          grid[i][j] = top + left;
        }
      }
      return grid[n - 1][m - 1] % 1_000_000_007;
    }
  }
}
