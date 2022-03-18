package programmers.Lv2;

import java.util.Stack;

public class P1829 {
  static class Solution {
    private static int[][] board;
    private int m, n;
    private boolean[][] visited;

    private int dfs(int x, int y) {
      int[] dx = { -1, 0, 1, 0 };
      int[] dy = { 0, 1, 0, -1 };
      int count = 1;
      Stack<int[]> stack = new Stack<>();
      stack.push(new int[] { x, y });
      visited[x][y] = true;
      while (!stack.isEmpty()) {
        int[] cur = stack.pop();
        int curX = cur[0];
        int curY = cur[1];
        for (int i = 0; i < 4; i++) {
          int nx = dx[i] + cur[0];
          int ny = dy[i] + cur[1];
          if (nx >= 0 && ny >= 0 && nx < m && ny < n) {
            if (!visited[nx][ny] && board[nx][ny] == board[curX][curY]) { // 첫 방문이고 이전 좌표의 색과 같다면
              visited[nx][ny] = true;
              stack.push(new int[] { nx, ny });
              count++;
            }
          }
        }
      }
      return count;
    }

    public int[] solution(int m, int n, int[][] picture) {
      this.m = m;
      this.n = n;
      this.board = new int[m][n];
      this.visited = new boolean[m][n];
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          board[i][j] = picture[i][j];
        }
      }

      int numberOfArea = 0;
      int maxSizeOfOneArea = 0;
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (board[i][j] != 0 && !visited[i][j]) {
            maxSizeOfOneArea = Math.max(maxSizeOfOneArea, dfs(i, j));
            numberOfArea++;
          }
        }
      }
      return new int[] { numberOfArea, maxSizeOfOneArea };
    }
  }

  public static void main(String[] args) {
    int m = 13;
    int n = 16;
    // int[][] picture = {
    // { 1, 1, 1, 0 },
    // { 1, 2, 2, 0 },
    // { 1, 0, 0, 1 },
    // { 0, 0, 0, 1 },
    // { 0, 0, 0, 3 },
    // { 0, 0, 0, 3 },
    // };
    int[][] picture = {
        { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 },
        { 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0 },
        { 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
        { 0, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0 },
        { 0, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 0 },
        { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
        { 0, 1, 3, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 0 },
        { 0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0 },
        { 0, 0, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 0, 0 },
        { 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0 },
        { 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 },
    };
    // int[][] picture = {
    // { 1, 1, 1, 0 },
    // { 1, 1, 1, 0 },
    // { 0, 0, 0, 1 },
    // { 0, 0, 0, 1 },
    // { 0, 0, 0, 1 },
    // { 0, 0, 0, 1 },
    // };
    for (int num : new Solution().solution(m, n, picture)) {
      System.out.print(num + ", ");
    }
    System.out.println();
  }
}

// 1 1 1 0
// 1 2 2 0
// 1 0 0 1
// 0 0 0 1
// 0 0 0 3
// 0 0 0 3