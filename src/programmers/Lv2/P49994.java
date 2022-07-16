package programmers.Lv2;

import java.util.HashSet;
import java.util.Set;

public class P49994 {
  public static void main(String[] args) {
    String dirs = "LULLLLLLU";
    System.out.println(new Solution().solution(dirs));
  }

  private static class Solution {
    private int[] curLocation;
    private Set<String> visitedEdges;

    public int solution(String dirs) {
      visitedEdges = new HashSet<>();
      curLocation = new int[2];
      char[] nextArr = dirs.toCharArray();
      for (char next : nextArr) {
        if (!isAvailable(next)) continue;
        move(next);
      }
      return visitedEdges.size();
    }

    private boolean isAvailable(char next) {
      int MAX = 5;
      int MIN = -5;
      return switch (next) {
        case 'U' -> curLocation[1] + 1 <= MAX;
        case 'D' -> curLocation[1] - 1 >= MIN;
        case 'R' -> curLocation[0] + 1 <= MAX;
        case 'L' -> curLocation[0] - 1 >= MIN;
        default -> throw new UnsupportedOperationException();
      };
    }

    private void move(char next) {
      int[] preLocation = new int[]{curLocation[0], curLocation[1]};
      switch (next) {
        case 'U' -> curLocation[1] += 1;
        case 'D' -> curLocation[1] -= 1;
        case 'R' -> curLocation[0] += 1;
        case 'L' -> curLocation[0] -= 1;
      }

      visitedEdges.add(String.valueOf(curLocation[0] + preLocation[0]) + (curLocation[1] + preLocation[1]));
    }
  }
}
