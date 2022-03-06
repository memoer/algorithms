package baekjoon.backtracking;

import java.util.Scanner;

public class P9663 {
  private static int result = 0;
  private static int[] grid;

  private static boolean isAvailable(int col, int row) {
    for (int preRow = 0; preRow < row; preRow++) {
      int preCol = grid[preRow];
      if (preCol == col || row - preRow == Math.abs(col - preCol)) {
        return false;
      }
    }
    return true;
  }

  private static void dfs(int row, final int N) {
    if (row == N) {
      result += 1;
      return;
    }
    for (int col = 0; col < N; col++) {
      if (isAvailable(col, row)) {
        grid[row] = col;
        dfs(row + 1, N);
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    final int N = sc.nextInt();
    sc.close();
    grid = new int[N];
    dfs(0, N);
    System.out.println(result);
  }
}
