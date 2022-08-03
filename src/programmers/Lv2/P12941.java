package programmers.Lv2;

import java.util.Arrays;

public class P12941 {
  public static void main(String[] args) {
    int[] A = {1,4,2};
    int[] B = {5,4,4};
    System.out.println(new Solution().solution(A, B));
  }

  private static class Solution {

    public int solution(int[] A, int[] B) {
      int answer = Integer.MAX_VALUE;
      int length = A.length;
      int[][] dp = new int[length][length];
      for (int i = 0; i < length; i++) {
        for (int j = 0; j < length; j++) dp[i][j] = A[i] * B[j];
      }
      for (int i = 0; i < length; i++) {
        int sum = dp[0][i];
        boolean[] visited = getVisited(length, i);
        for (int j = 1; j < length; j++) {
          int k = 0;
          while (visited[k]) k++;
          sum += dp[j][k];
          visited[k] = true;
        }
        answer = Math.min(answer, sum);
      }
      for (int[] ints : dp) {
        for (int anInt : ints) System.out.print(anInt+", ");
        System.out.println();
      }
      return answer;
    }

    private boolean[] getVisited(int length, int idx) {
      boolean[] visited = new boolean[length];
      visited[idx] = true;
      return visited;
    }
  }
}
