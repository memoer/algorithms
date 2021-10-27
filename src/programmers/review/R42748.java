package programmers.review;

import java.util.Arrays;

public class R42748 {
  static class Solution {
    public int[] solution(int[] array, int[][] commands) {
      int[] answer = new int[commands.length];
      int curIdx = 0;
      for (int[] cmd : commands) {
        int from = cmd[0] - 1;
        int to = cmd[1];
        int idx = cmd[2] - 1;
        int[] temp = Arrays.copyOfRange(array, from, to);
        Arrays.sort(temp);
        answer[curIdx++] = temp[idx];
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] array = { 1, 5, 2, 6, 3, 7, 4 };
    int[][] commands = { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } };
    System.out.println(new Solution().solution(array, commands));
  }
}
