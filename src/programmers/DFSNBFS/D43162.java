package programmers.DFSNBFS;

import java.util.Stack;

public class D43162 {
  static class Solution {

    public int solution(int n, int[][] computers) {
      int answer = 0;
      Stack<Integer> needVisit = new Stack<>();
      boolean[] visited = new boolean[n];
      for (int computer = 0; computer < n; computer++) {
        if (visited[computer]) {
          continue;
        }
        needVisit.push(computer);
        while (!needVisit.isEmpty()) {
          int cIdx = needVisit.pop();
          visited[cIdx] = true;
          for (int i = 0; i < n; i++) {
            if (cIdx == i || computers[cIdx][i] == 0) {
              continue;
            }
            if (!visited[i]) {
              needVisit.push(i);
            }
          }
        }
        answer++;
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int n = 3;
    int[][][] testCase = { { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } }, { { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 } } };
    for (int[][] computers : testCase) {
      System.out.println(new Solution().solution(n, computers));
    }
  }
}
