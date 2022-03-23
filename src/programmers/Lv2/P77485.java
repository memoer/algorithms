package programmers.Lv2;

import java.util.PriorityQueue;

public class P77485 {
  static class Solution {
    private int[][] grid;
    private int[] answer;
    private int[] query;
    private int stored = 0;
    PriorityQueue<Integer> pq;
    private final int START_ROW = 0;
    private final int START_COL = 1;
    private final int END_ROW = 2;
    private final int END_COL = 3;

    private void init(int rows, int columns, int qLength) {
      answer = new int[qLength];
      grid = new int[rows][columns];
      pq = new PriorityQueue<>();

      int temp = 1;
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
          grid[i][j] = temp++;
        }
      }
    }

    private void toRightOnTop() {
      // int startRow, int startCol, int endCol
      int temp = 0;
      for (int i = query[START_COL] - 1; i < query[END_COL] - 1; i++) {
        temp = grid[query[START_ROW] - 1][i + 1];
        grid[query[START_ROW] - 1][i + 1] = stored;
        stored = temp;
        pq.add(stored);
      }
    }

    private void toBottomOnRight() {
      // int startRow, int startCol, int endRow
      int temp = 0;
      for (int i = query[START_ROW] - 1; i < query[END_ROW] - 1; i++) {
        temp = grid[i + 1][query[END_COL] - 1];
        grid[i + 1][query[END_COL] - 1] = stored;
        stored = temp;
        pq.add(stored);
      }
    }

    private void toLeftOnBottom() {
      // int startRow, int startCol, int endCol
      int temp = 0;
      for (int i = query[END_COL] - 1; i > query[START_COL] - 1; i--) {
        temp = grid[query[END_ROW] - 1][i - 1];
        grid[query[END_ROW] - 1][i - 1] = stored;
        stored = temp;
        pq.add(stored);
      }
    }

    private void toTopOnLeft() {
      // int startRow, int startCol, int endRow
      int temp = 0;
      for (int i = query[END_ROW] - 1; i > query[START_ROW] - 1; i--) {
        temp = grid[i - 1][query[START_COL] - 1];
        grid[i - 1][query[START_COL] - 1] = stored;
        stored = temp;
        pq.add(stored);
      }
    }

    public int[] solution(int rows, int columns, int[][] queries) {
      int qLength = queries.length;
      init(rows, columns, qLength);
      for (int i = 0; i < qLength; i++) {
        query = queries[i];
        stored = grid[queries[i][START_ROW] - 1][queries[i][START_COL] - 1];
        pq.add(stored);
        toRightOnTop();
        toBottomOnRight();
        toLeftOnBottom();
        toTopOnLeft();
        answer[i] = pq.poll();
        pq.clear();
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int rows = 6;
    int columns = 6;
    int[][] queries = {
        { 2, 2, 5, 4 },
        { 3, 3, 6, 6 },
        { 5, 1, 6, 3 },
    };
    for (int num : new Solution().solution(rows, columns, queries)) {
      System.out.print(num + ", ");
    }
    System.out.println();
  }

}
