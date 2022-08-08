package programmers.Lv3;

public class P43105 {
  public static void main(String[] args) {
    int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
    System.out.println(new Solution().solution(triangle));
  }

  private static class Solution {
    public int solution(int[][] triangle) {
      int max = Integer.MIN_VALUE;
      int rowLength = triangle.length;
      for (int row = 1; row < rowLength; row++) {
        int colLength = triangle[row].length;
        for (int col = 0; col < colLength; col++) {
          if (col == 0) {
            triangle[row][0] += triangle[row - 1][0];
          } else if (col == colLength - 1) {
            triangle[row][col] += triangle[row - 1][col - 1];
          } else {
            int left = triangle[row - 1][col - 1];
            int right = triangle[row - 1][col];
            triangle[row][col] += Math.max(left, right);
          }
          max = Math.max(max, triangle[row][col]);
        }
      }
      return max;
    }
  }
}
