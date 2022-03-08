package programmers.level1;

import java.util.stream.IntStream;

public class P42748 {
  static class Solution {
    public int[] solution(int[] array, int[][] commands) {
      int cLength = commands.length;
      int[] answer = new int[cLength];
      int cur = 0;
      for (int i = 0; i < cLength; i++) {
        int start = commands[i][0];
        int end = commands[i][1];
        int target = commands[i][2];
        answer[cur++] = IntStream.range(0, end - start + 1).map(idx -> array[idx + start - 1]).sorted()
            .toArray()[target - 1];
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] array = { 1, 5, 2, 6, 3, 7, 4 };
    int[][] commands = {
        { 2, 5, 3 },
        { 4, 4, 1 },
        { 1, 7, 3 },
    };
    for (int n : new Solution().solution(array, commands)) {
      System.out.print(n + ", ");
    }
    System.out.println();
  }
}
