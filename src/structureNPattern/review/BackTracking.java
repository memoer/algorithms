package structureNPattern.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 백트래킹의 대표적인 문제 -> NQueen
public class BackTracking {
  // 0 1 2 3
  // 0 1 2 3
  // 0 1 2 3
  // 0 1 2 3
  static class Solution {
    private List<int[]> answer = new ArrayList<>();

    private boolean isAvailable(int curRow, int candidateCol, int[] candidate) {
      for (int preRow = 0; preRow < curRow; preRow++) {
        int preCol = candidate[preRow];
        int diffRow = curRow - preRow;
        if (preCol == candidateCol || diffRow == Math.abs(candidateCol - preCol)) {
          return false;
        }
      }
      return true;
    }

    private void dfs(int N, int curRow, int curCol, int[] candidate) {
      if (curRow == N) {
        answer.add(Arrays.copyOf(candidate, candidate.length));
        return;
      }
      for (int i = 0; i < N; i++) {
        if (isAvailable(curRow, i, candidate)) {
          candidate[curRow] = i;
          dfs(N, curRow + 1, i, candidate);
          candidate[curRow] = 0;
        }
      }
    }

    public List<int[]> solution(int N) {
      dfs(N, 0, 0, new int[N]);
      return answer;
    }
  }

  public static void main(String[] args) {
    int N = 4;
    for (int[] a : new Solution().solution(N)) {
      for (int num : a) {
        System.out.print(num + ", ");
      }
      System.out.println();
    }
  }
}
