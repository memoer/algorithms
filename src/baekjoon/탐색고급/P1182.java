package baekjoon.탐색고급;

import java.util.Scanner;

public class P1182 {
  public static void main(String[] args) {
    new Solution().solution(args);
  }

  private static class Solution {
    int[] arr;
    int answer;
    int N;
    int S;
    int size;

    public void solution(String[] args) {
      Scanner scanner = new Scanner(System.in);
      this.N = scanner.nextInt();
      this.S = scanner.nextInt();
      this.arr = new int[N];
      for (int i = 0; i < N; i++) arr[i] = scanner.nextInt();

      answer = 0;
      for (int size = 1; size <= N; size++) {
        this.size = size;
        dfs(0, 0, 0);
      }

      System.out.println(answer);
    }

    private void dfs(int called, int sum, int start) {
      if (called == this.size) {
        if (sum == this.S) answer += 1;
        return;
      }
      for (int i = start; i < this.N; i++) {
        dfs(called + 1, sum + this.arr[i], i + 1);
      }
    }
  }
}
