package structureNPattern.backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueen {
  private static List<List<Integer>> result = new ArrayList<>();
  private static int BOARD_SIZE = 4;

  private static List<Integer> deepCopy(List<Integer> list) {
    List<Integer> c = new ArrayList<>();
    for (int i : list) {
      c.add(i);
    }
    return c;
  }

  private static boolean isAvailable(int curCol, List<Integer> candidate) {
    int curRow = candidate.size();
    for (int preRow = 0; preRow < curRow; preRow++) {
      int preCol = candidate.get(preRow);
      if (preCol == curCol || Math.abs(preCol - curCol) == curRow - preRow) {
        return false;
      }
    }
    return true;
  };

  private static void backTracking(int row, List<Integer> candidate) {
    if (row == BOARD_SIZE) {
      List<Integer> c = deepCopy(candidate);
      result.add(c);
      return;
    }
    for (int col = 0; col < BOARD_SIZE; col++) {
      if (isAvailable(col, candidate)) {
        candidate.add(col);
        backTracking(row + 1, candidate);
        candidate.remove(candidate.size() - 1);
      }
    }
  }

  private static void print() {
    for (List<Integer> d : result) {
      System.out.println(d);
    }
  }

  public static void main(String[] args) throws Exception {
    backTracking(0, new ArrayList<>());
    print();
  }
}
