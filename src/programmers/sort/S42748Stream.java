package programmers.sort;

import java.util.Arrays;

public class S42748Stream {
  static class Solution {
    public int[] solution(int[] array, int[][] commands) {
      int commandsLength = commands.length;
      int[] answer = new int[commandsLength];
      for (int i = 0; i < commandsLength; i++) {
        int from = commands[i][0] - 1;
        int to = commands[i][1];
        int k = commands[i][2] - 1;
        answer[i] = Arrays.stream(array).skip(from).limit(to - from).sorted().toArray()[k];
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] array = { 1, 5, 2, 6, 3, 7, 4 };
    int[][] commands = { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } };
    new Solution().solution(array, commands);
  }
}
