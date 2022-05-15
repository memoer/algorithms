package flab;

import java.util.ArrayList;
import java.util.List;

public class D20220515_3 {
  public static void main(String[] args) {
    char[][] map = {{'H', '0', '0', '0'}, {'H', '0', '0', '0'}, {'0', '0', '0', 'H'}, {'0', '0', '0', 'H'}};
    int n = 4;
    List<char[][]> plant = new Solution().plant(n, map);
    for (char[][] chars : plant) {
      for (char[] aChar : chars) {
        for (char c : aChar) {
          System.out.print(c + ", ");
        }
        System.out.println();
      }
      System.out.println("-----------");
    }
  }

  static class Solution {
    boolean[] rowExisted;
    List<char[][]> result;

    public List<char[][]> plant(int n, char[][] map) {
      this.rowExisted = new boolean[n];
      this.result = new ArrayList<>();
      return result;
    }
  }
}
