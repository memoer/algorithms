package programmers.DP;

public class D42898 {
  static class Solution {
    private int[][] getBoard(int m, int n, int[][] puddles) {
      int[][] board = new int[n][m];
      board[0][0] = 1;
      for (int i = 0; i < puddles.length; i++) {
        board[puddles[i][1] - 1][puddles[i][0] - 1] = -1;
      }
      return board;
    }

    public int solution(int m, int n, int[][] puddles) {
      int[][] board = getBoard(m, n, puddles);
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
          if (board[i][j] == -1) {
            // 웅덩이
            continue;
          }
          if (i - 1 >= 0 && board[i - 1][j] != -1) {
            // 효율성에서 공간복잡도까지 체크를 하나보다
            // -> https://programmers.co.kr/questions/16262
            board[i][j] += board[i - 1][j] % 1000000007;
          }
          if (j - 1 >= 0 && board[i][j - 1] != -1) {
            board[i][j] += board[i][j - 1] % 1000000007;
          }
        }
      }
      return board[n - 1][m - 1] % 1000000007;
    }
  }

  public static void main(String[] args) {
    int m = 1, n = 8;
    int[][] puddles = { { 1, 4 } };
    System.out.println(new Solution().solution(m, n, puddles));
  }
}
