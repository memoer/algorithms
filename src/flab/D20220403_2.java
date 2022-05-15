package flab;

public class D20220403_2 {
  public static void main(String[] args) {
    char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0',
            '1', '1'}};
    System.out.println(new Solution().numIslands(grid));
  }

  static class Solution {
    private char[][] grid;
    private int LIMIT_Y;
    private int LIMIT_X;

    private void visit(int y, int x) {
      this.grid[y][x] = 'x';
    }

    private boolean isVisited(int y, int x) {
      return grid[y][x] == 'x';
    }

    private boolean isLand(int y, int x) {
      return grid[y][x] == '1';
    }

    private boolean isIn(int y, int x) {
      return y >= 0 && y < this.LIMIT_Y && x >= 0 && x < this.LIMIT_X;
    }

    private boolean isAvailable(int y, int x) {
      return this.isIn(y, x) && this.isLand(y, x) && !this.isVisited(y, x);
    }

    private void find(int y, int x) {
      visit(y, x);
      if (this.isAvailable(y - 1, x)) find(y - 1, x);
      if (this.isAvailable(y + 1, x)) find(y + 1, x);
      if (this.isAvailable(y, x - 1)) find(y, x - 1);
      if (this.isAvailable(y, x + 1)) find(y, x + 1);
    }

    public int numIslands(char[][] grid) {
      int answer = 0;
      this.grid = grid;
      this.LIMIT_Y = grid.length;
      this.LIMIT_X = grid[0].length;

      for (int y = 0; y < this.LIMIT_Y; y++) {
        for (int x = 0; x < this.LIMIT_X; x++) {
          if (!this.isLand(y, x) || this.isVisited(y, x)) continue;
          find(y, x);
          answer += 1;
        }
      }

      return answer;
    }
  }
}
