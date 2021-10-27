package programmers.stackNQueue;

import java.util.ArrayList;
import java.util.List;

public class S42586 {
  static class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
      List<Integer> answer = new ArrayList<>();
      int completedJob = 0;
      while (completedJob != progresses.length) {
        int deployedNumber = 0;
        for (int i = 0; i < progresses.length; i++) {
          progresses[i] += speeds[i];
          if (progresses[i] >= 100 && i == completedJob) {
            deployedNumber += 1;
            completedJob += 1;
          }
        }
        if (deployedNumber != 0) {
          answer.add(deployedNumber);
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] progresses = { 95, 90, 99, 99, 80, 99 };
    int[] speeds = { 1, 1, 1, 1, 1, 1 };
    System.out.println(new Solution().solution(progresses, speeds));
  }
}
