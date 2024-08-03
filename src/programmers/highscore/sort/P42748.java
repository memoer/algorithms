package programmers.highscore.sort;

import java.util.ArrayList;
import java.util.List;

public class P42748 {

  public static void main(String[] args) {
    int[] array = new int[]{1, 5, 2, 6, 3, 7, 4};
    int[][] commands = new int[][]{
        {2, 5, 3},
        {4, 4, 1},
        {1, 7, 3}
    };
    int[] solution = new Solution().solution(array, commands);
    for (int i : solution) {
      System.out.printf("%d, ", i);
    }
    System.out.println();
  }

  private static class Solution {

    public int[] solution(int[] array, int[][] commands) {
      int len = commands.length;
      int[] answer = new int[len];
      List<Integer> list = new ArrayList<>();

      for (int i = 0; i < len; i++) {
        int start = commands[i][0] - 1;
        int end = commands[i][1] - 1;
        int k = commands[i][2] - 1;
        for (; start <= end; start++) {
          list.add(array[start]);
        }
        list.sort(Integer::compare);
        answer[i] = list.get(k);
        list.clear();
      }
      return answer;
    }
  }
}
