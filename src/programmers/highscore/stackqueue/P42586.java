package programmers.highscore.stackqueue;

import java.util.ArrayList;
import java.util.List;

public class P42586 {

  public static void main(String[] args) {
    int[] progresses = new int[]{99, 96, 94};
    int[] speeds = new int[]{1, 3, 4,};
    int[] solution = new Solution().solution(progresses, speeds);

    for (int i : solution) {
      System.out.printf("%d, ", i);
    }
    System.out.println();
  }

  private static class Solution {

    public int[] solution(int[] progresses, int[] speeds) {
      int i = 0;
      int len = progresses.length;
      List<Integer> result = new ArrayList<>();
      while (i < len) {
        int days = 1;
        int acc = 0;
        int rest = 100 - progresses[i];
        while (rest > speeds[i] * days) {
          days += 1;
        }
        while (i < len) {
          if (progresses[i] + (speeds[i] * days) < 100) {
            break;
          }
          acc += 1;
          i += 1;
        }
        result.add(acc);
      }
      return result.stream().mapToInt(Integer::intValue).toArray();
    }
  }
}
