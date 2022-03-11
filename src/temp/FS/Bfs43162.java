package temp.FS;

import java.util.LinkedList;
import java.util.Queue;

public class Bfs43162 {
  public static void main(String[] args) {
    int n = 3;
    int[][][] testCase = { { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } }, { { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 } } };
    for (int[][] computers : testCase) {
      System.out.println(new Solution().solution(n, computers));
    }
  }

  static class Solution {

    // BFS 사용
    public int solution(int n, int[][] computers) {
      int result = 0;
      boolean[] visited = new boolean[n];
      Queue<Integer> needVisit = new LinkedList<>();

      for (int com = 0; com < n; com++) {
        if (visited[com]) {
          continue;
        }
        needVisit.add(com);
        while (!needVisit.isEmpty()) {
          int cIdx = needVisit.poll();
          visited[cIdx] = true;
          for (int i = 0; i < n; i++) {
            if (i == cIdx) {
              continue;
            }
            if (computers[cIdx][i] == 1 && !visited[i]) {
              needVisit.add(i);
            }
          }
        }
        result += 1;
      }

      return result;
    }
  }
}
