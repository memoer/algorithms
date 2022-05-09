package flab;

// https://leetcode.com/problems/number-of-islands/
public class P200 {
  public static void main(String[] args) {
    char[][] grid = {{'1', '1', '1'}, {'0', '1', '0'}, {'1', '1', '1'}};
    System.out.println(new Solution().numIslands(grid));
  }

  static class Solution {
    private char[][] grid;
    private int outterLength;
    private int innerLength;
    private boolean[][] isVisited;

    private boolean isIn(int i, int j) {
      return i >= 0 && i < this.outterLength && j >= 0 && j < this.innerLength;
    }

    private boolean isAvailable(int i, int j) {
      return isIn(i, j) && this.grid[i][j] == '1' && !this.isVisited[i][j];
    }

    private void findIsland(int i, int j) {
      this.isVisited[i][j] = true;
      if (this.isAvailable(i - 1, j)) {
        findIsland(i - 1, j);
      }
      if (this.isAvailable(i + 1, j)) {
        findIsland(i + 1, j);
      }
      if (this.isAvailable(i, j - 1)) {
        findIsland(i, j - 1);
      }
      if (this.isAvailable(i, j + 1)) {
        findIsland(i, j + 1);
      }
    }


    public int numIslands(char[][] grid) {
      int answer = 0;
      this.grid = grid;
      this.outterLength = grid.length;
      this.innerLength = grid[0].length;
      this.isVisited = new boolean[this.outterLength][this.innerLength];

      for (int i = 0; i < this.outterLength; i++) {
        for (int j = 0; j < this.innerLength; j++) {
          if (grid[i][j] == '0' || this.isVisited[i][j]) continue;
          this.findIsland(i, j);
          answer += 1;
        }
      }
      return answer;
    }
  }
}
