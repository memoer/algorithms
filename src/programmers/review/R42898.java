package programmers.review;

public class R42898 {
  static class Solution {
    public int solution(int m, int n, int[][] puddles) {
      int[][] answer = new int[n][m];
      answer[0][0] = 1;
      for (int[] puddle : puddles) {
        int x = puddle[0] - 1;
        int y = puddle[1] - 1;
        answer[y][x] = -1;
      }
      for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
          if (answer[y][x] == -1) {
            continue;
          }
          if (x - 1 >= 0 && answer[y][x - 1] != -1) {
            answer[y][x] += answer[y][x - 1] % 1000000007;
          }
          if (y - 1 >= 0 && answer[y - 1][x] != -1) {
            answer[y][x] += answer[y - 1][x] % 1000000007;
          }
        }
      }
      return answer[n - 1][m - 1] % 1000000007;
    }
  }

  public static void main(String[] args) {
    int m = 4;
    int n = 3;
    int[][] puddles = { { 2, 2 } };
    System.out.println(new Solution().solution(m, n, puddles));
  }
}
