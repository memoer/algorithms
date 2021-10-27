package programmers.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 백트래킹의 대표적인 문제
public class NQueen {
  // 0 1 2 3
  // 0 1 2 3
  // 0 1 2 3
  // 0 1 2 3
  static class Solution {
    private int N;
    private List<int[]> answer = new ArrayList<>();

    // promising -> 조건 검색
    private boolean isAvailable(int curRow, int candidateCol, int[] candidate) {
      for (int preRow = 0; preRow < curRow; preRow++) {
        int preCol = candidate[preRow];
        int diff = Math.abs(preRow - curRow);
        if (candidateCol == preCol || candidateCol == preCol - diff || candidateCol == preCol + diff) {
          return false;
        }
      }
      return true;
    }

    // 한 곳으로 끝까지 검색하여, target과 일치하는 지 확인
    private void dfs(int curRow, int[] candidate) {
      if (curRow == this.N) {
        answer.add(Arrays.copyOf(candidate, candidate.length));
        return;
      }
      // column의 갯수 -> 총 4번의 반복문이 돈다.
      for (int candidateCol = 0; candidateCol < this.N; candidateCol++) {
        // 특정 row에서 모든 col이 조건에 충족하지 않는다면 -> 재귀가 일어나지 않는다.
        // 그 다음의 row 가 검사되지 않음 -> 가지치기
        if (isAvailable(curRow, candidateCol, candidate)) {
          candidate[curRow] = candidateCol;
          // row의 갯수 -> 총 4번의 재귀가 일어난다.
          dfs(curRow + 1, candidate);
          candidate[curRow] = -1;
        }
      }
    }

    public List<int[]> solution(int N) {
      this.N = N;
      int[] candidate = new int[N];
      Arrays.fill(candidate, -1);
      dfs(0, candidate);
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
