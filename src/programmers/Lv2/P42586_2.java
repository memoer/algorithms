package programmers.Lv2;

import java.util.*;

public class P42586_2 {
  public static void main(String[] args) {
    int[] progresses = {95, 90, 99,99,80,99};
    int[] speeds = {1, 1, 1,1,1,1};
    for (int num : new P42586_2.Solution().solution(progresses, speeds)) System.out.print(num + ", ");
    System.out.println();
  }

  private static class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
      List<Integer> result = new ArrayList<>();
      int length = progresses.length;
      int deployed = 0;
      while (deployed < length) {
        int before = deployed;
        for (int i = 0; i < length; i++) progresses[i] += speeds[i];
        while (deployed < length && progresses[deployed] >= 100) deployed += 1;
        if (before != deployed) result.add(deployed - before);
      }
      return result.stream().mapToInt(Integer::valueOf).toArray();
    }
  }
}
