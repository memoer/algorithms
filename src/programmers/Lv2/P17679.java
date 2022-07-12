package programmers.Lv2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class P17679 {
  public static void main(String[] args) {
    int m = 5;
    int n = 6;
    String[] board = {
            "AAAAAA",
            "BBAATB",
            "BBAATB",
            "JJJTAA",
            "JJJTAA",
    };
    System.out.println(new Solution().solution(m, n, board));
  }

  private static class Solution {
    private int m;
    private int n;
    private char[][] board;
    private final char BLANK = '\0';

    /*
    1. 4개 정사각형[블록]이 모이면 지워진다.
    2. 입력으로 블록의 첫 배치가 주어졌을 때, 지워지는 블록은 모두 몇 개인지 판단하는 프로그램을 제작하라.
     */
    /*
    1. 판의 높이 m[배열의 길이], 폭 n[각 배열 원소의 문자열 길이], 배치 정보 board
    2. 2<=n, m<=30
     */
    public int solution(int m, int n, String[] board) {
      this.m = m;
      this.n = n;
      this.board = new char[m][];
      // O(m)
      for (int i = 0; i < m; i++) this.board[i] = board[i].toCharArray();

      int answer = 0;
      Set<Block> search;

      while ((search = search()).size() != 0) {
        answer += remove(search);
        reorder();
      }
      return answer;
    }


    private Set<Block> search() {
      Set<Block> set = new HashSet<>();
      // O(mn)
      for (int row = 0; row < m - 1; row++) {
        for (int col = 0; col < n - 1; col++) {
          if (isMatched(row, col)) addMatchedBlocksToSet(row, col, set);
        }
      }
      return set;
    }

    private boolean isMatched(int row, int col) {
      if (board[row][col] == BLANK) return false;
      // 시간복잡도 고려 필요 없음
      else if (board[row][col] != board[row][col + 1]) return false;
      else if (board[row][col] != board[row + 1][col]) return false;
      else return board[row + 1][col] == board[row + 1][col + 1];
    }

    private void addMatchedBlocksToSet(int row, int col, Set<Block> set) {
      // 시간 복잡도 고려 필요 없음
      set.add(new Block(row, col));
      set.add(new Block(row, col + 1));
      set.add(new Block(row + 1, col));
      set.add(new Block(row + 1, col + 1));
    }

    private int remove(Set<Block> search) {
      for (Block block : search) board[block.row][block.col] = BLANK;
      return search.size();
    }

    private void reorder() {
      for (int row = m - 2; row >= 0; row--) {
        for (int col = 0; col < n; col++) {
          if (board[row][col] != BLANK && board[row + 1][col] == BLANK) {
            int r = row;
            while (r + 1 < m && board[r + 1][col] == BLANK) r++;
            board[r][col] = board[row][col];
            board[row][col] = BLANK;
          }
        }
      }
    }

    private static class Block {
      private final int row;
      private final int col;

      public Block(int row, int col) {
        this.row = row;
        this.col = col;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return row == block.row && col == block.col;
      }

      @Override
      public int hashCode() {
        return Objects.hash(row, col);
      }
    }
  }
}
