package programmers.Lv2;

import java.util.ArrayList;
import java.util.List;

public class P42586 {
  static class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
      List<Integer> answer = new ArrayList<>();
      int idx = 0;
      while (idx < progresses.length) {
        int deployed = 0;
        for (int i = idx; i < progresses.length; i++) {
          progresses[i] += speeds[i];
        }
        for (; idx < progresses.length; idx++) {
          if (progresses[idx] < 100) {
            break;
          }
          deployed += 1;
        }
        if (deployed != 0) {
          answer.add(deployed);
        }
      }
      return answer.stream().mapToInt(Integer::valueOf).toArray();
    }
  }

  public static void main(String[] args) {
    int[] progresses = { 93, 30, 55 };
    int[] speeds = { 1, 30, 5 };
    for (int num : new Solution().solution(progresses, speeds)) {
      System.out.print(num);
    }
    System.out.println();
  }

}
