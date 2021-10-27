package programmers.DP;

public class D43105 {
  static class Solution {
    public int solution(int[][] triangle) {
      int answer = 0;
      int[][] sumList = new int[triangle.length][];
      for (int i = 0; i < triangle.length; i++) {
        sumList[i] = new int[triangle[i].length];
        if (i == 0) {
          sumList[0][0] = triangle[0][0];
        }
      }
      for (int curDepth = 1; curDepth < triangle.length; curDepth++) {
        for (int curIdx = 0; curIdx < triangle[curDepth].length; curIdx++) {
          int sumA = 0, sumB = 0;
          if (curIdx < triangle[curDepth - 1].length) {
            sumA = triangle[curDepth][curIdx] + sumList[curDepth - 1][curIdx];
          }
          if (curIdx - 1 >= 0) {
            sumB = triangle[curDepth][curIdx] + sumList[curDepth - 1][curIdx - 1];
          }
          int max = Math.max(sumA, sumB);
          if (curDepth != triangle.length - 1) {
            sumList[curDepth][curIdx] = max;
          } else if (max > answer) {
            answer = max;
          }
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[][] triangle = { { 7 }, { 3, 8 }, { 8, 1, 0 }, { 2, 7, 4, 4 }, { 4, 5, 2, 6, 5 } };
    System.out.println(new Solution().solution(triangle));
  }
}

// 18,11,16,15