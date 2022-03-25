package programmers.Lv2;

import java.util.ArrayList;
import java.util.List;

public class P81032 {
  static class Solution {
    List<char[][]> list;
    private boolean isFollw;
    private byte curIdx;
    private byte curX;
    private byte curY;

    private void initialize(String[][] places) {
      list = new ArrayList<>();
      for (String[] place : places) {
        char[][] temp = new char[5][5];
        int length = place.length;
        for (int i = 0; i < length; i++) {
          temp[i] = place[i].toCharArray();
        }
        list.add(temp);
      }
    }

    private void check(int y, int x, int move) {
      if (move == 1) {
        isFollw = false;
      }
      if (move == 2) {
        if (y == curY) {
          // 왼쪽
          if (list.get(curIdx)[y][x + 1] != 'X') {
            isFollw = false;
          }
        } else if (x == curX) {
          // 위
          if (list.get(curIdx)[y + 1][x] != 'X') {
            isFollw = false;
          }
        } else if (list.get(curIdx)[y][x + 1] != 'X' || list.get(curIdx)[y + 1][x] != 'X') {
          // 대각선
          isFollw = false;
        }
      }
    }

    private void dfs(int y, int x, int move) {
      if (move > 2) {
        return;
      }
      if (move != 0 && list.get(curIdx)[y][x] == 'P') {
        check(y, x, move);
      }
      if (!isFollw) {
        return;
      }
      if (y - 1 >= 0) {
        dfs(y - 1, x, move + 1);
      }
      if (x - 1 >= 0) {
        dfs(y, x - 1, move + 1);
      }
    }

    public int[] solution(String[][] places) {
      int[] answer = new int[5];
      initialize(places);
      for (curIdx = 0; curIdx < 1; curIdx++) {
        isFollw = true;
        byte y = 0;
        while (isFollw && y < 5) {
          byte x = 0;
          while (isFollw && x < 5) {
            curY = y;
            curX = x;
            if (list.get(curIdx)[y][x] == 'P') {
              dfs(y, x, 0);
            }
            x += 1;
          }
          y += 1;
        }
        answer[curIdx] = isFollw ? 1 : 0;
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    String[][] places = {
        { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
        { "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" },
        { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
        { "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" },
        { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" },
    };
    for (int num : new Solution().solution(places)) {
      System.out.print(num + ", ");
    }
    System.out.println();
  }

}
