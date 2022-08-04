package programmers.Lv2;

public class P12952 {
  public static void main(String[] args) {
    System.out.println(new Solution().solution(4));
  }

  private static class Solution {
    private int answer;
    private int n;
    private int[] board;

    public int solution(int n) {
      this.answer = 0;
      this.n = n;
      this.board = new int[n];
      for (int i = 0; i < n; i++) board[i] = -1;
      for (int col = 0; col < n; col++) {
        board[0] = col;
        check(1);
        board[0] = -1;
      }
      return answer;
    }

    private boolean isAvailable(int l, int col) {
      for (int preRow = 0; preRow < l; preRow++) {
        int preCol = board[preRow];
        if (col == preCol || Math.abs(preCol - col) == l - preRow) return false;
      }
      return true;
    }

    private void check(int row) {
      if (row == n) {
        answer += 1;
        return;
      }
      for (int col = 0; col < n; col++) {
        if (!isAvailable(row, col)) continue;
        board[row] = col;
        check(row + 1);
        board[row] = -1;
      }
    }
  }
}
