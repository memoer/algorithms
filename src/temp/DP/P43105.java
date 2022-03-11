package temp.DP;

public class P43105 {
  public static void main(String[] args) {
    int[][] triangle = {
        { 7 },
        { 3, 8 },
        { 8, 1, 0 },
        { 2, 7, 4, 4 },
        { 4, 5, 2, 6, 5 },
    };
    System.out.println(new Solution().solution(triangle));
  }

  public static class Solution {
    public int solution(int[][] triangle) {
      int result = 0;
      for (int i = 1; i < triangle.length; i++) {
        triangle[i][0] += triangle[i - 1][0];
        triangle[i][i] += triangle[i - 1][i - 1];
        for (int j = 1; j < triangle[i].length - 1; j++) {
          triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
        }
      }
      for (int i = 0; i < triangle[triangle.length - 1].length; i++) {
        result = Math.max(triangle[triangle.length - 1][i], result);
      }
      return result;
    }
  }
}
