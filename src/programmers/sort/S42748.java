package programmers.sort;

import java.util.Arrays;

public class S42748 {
  static class Solution {
    public int[] solution(int[] array, int[][] commands) {
      int[] answer = new int[commands.length];
      int curIdx = 0;
      for (int[] command : commands) {
        int i = command[0];
        int j = command[1];
        int k = command[2];
        int[] newArray = Arrays.copyOfRange(array, i - 1, j);
        Arrays.sort(newArray);

        answer[curIdx++] = newArray[k - 1];
      }
      return answer;
    }
  }

  public static int[] main(String[] args) {
    int[] array = { 1, 5, 2, 6, 3, 7, 4 };
    int[][] commands = { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } };
    return new Solution().solution(array, commands);
  }

  public static void print(int[] list) {
    StringBuilder sb = new StringBuilder();
    for (int i : list) {
      sb.append(i);
    }
    System.out.println(String.join(", ", sb).toString());
  }
}
