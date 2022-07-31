package programmers.Lv2;

public class P12905 {
  public static void main(String[] args) {
    int[][] board = {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}};
    for (int[] a : board) {
      for (int b : a) System.out.print(b + ", ");
      System.out.println();
    }
    System.out.println("---");
    System.out.println(new Solution().solution(board));
  }


  private static class Solution {
    // 1 <= row <= 1,000 -> a
    // 1 <= col <= 1,000 -> b
    public int solution(int[][] board) {
      int rowLength = board.length;
      int colLength = board[0].length;
      int[][] dp = new int[rowLength + 1][colLength + 1];
      int answer = 0;
      for (int i = 1; i < rowLength + 1; i++) {
        for (int j = 1; j < colLength + 1; j++) {
          if (board[i - 1][j - 1] == 0) continue;
          dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
          if (dp[i][j] <= answer) continue;
          answer = dp[i][j];
        }
      }
      for (int[] a : dp) {
        for (int b : a) System.out.print(b + ", ");
        System.out.println();
      }
      return answer * answer;
    }
  }
}
