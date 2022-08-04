package programmers.Lv2;

public class P12949 {
  public static void main(String[] args) {
    int[][] arr1 = {{1, 4}, {3, 2}, {4, 1}};
    int[][] arr2 = {{3}, {3}};
    for (int[] a : new Solution().solution(arr1, arr2)) {
      for (int b : a) System.out.print(b + ", ");
      System.out.println();
    }
  }

  private static class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
      int a = arr1.length;
      int b = arr2[0].length;
      int c = arr1[0].length;
      int[][] answer = new int[a][b];
      for (int i = 0; i < a; i++) {
        for (int j = 0; j < b; j++) {
          for (int k = 0; k < c; k++) answer[i][j] += (arr1[i][k] * arr2[k][j]);
        }
      }
      return answer;
    }
  }
}
