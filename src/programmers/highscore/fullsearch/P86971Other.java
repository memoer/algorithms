package programmers.highscore.fullsearch;

public class P86971Other {

  public static void main(String[] args) {
    int n = 9;
    int[][] wires = new int[][]{
        {1, 3},
        {2, 3},
        {3, 4},
        {4, 5},
        {4, 6},
        {4, 7},
        {7, 8},
        {7, 9}
    };
    new Solution().solution(9, wires);
  }

  private static class Solution {


    int N, min;
    int[][] map;
    int[] vst;

    int dfs(int n) {
      vst[n] = 1;
      int child = 1;
      for (int i = 1; i <= N; i++) {
        if (vst[i] == 0 && map[n][i] == 1) {
          vst[i] = 1;
          child += dfs(i);
        }
      }
      min = Math.min(min, Math.abs(child - (N - child)));
      return child;
    }

    public int solution(int n, int[][] wires) {
      N = n;
      min = n;
      map = new int[n + 1][n + 1];
      vst = new int[n + 1];
      for (int[] wire : wires) {
        int a = wire[0], b = wire[1];
        map[a][b] = map[b][a] = 1;
      }

      for (int[] ints : map) {
        for (int anInt : ints) {
          System.out.printf("%d, ", anInt);
        }
        System.out.println();
      }
      dfs(1);
      return min;
    }
  }

}
