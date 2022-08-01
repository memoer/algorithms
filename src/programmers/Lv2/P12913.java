package programmers.Lv2;

public class P12913 {
  public static void main(String[] args) {
    int[][] land = {{1, 2, 3, 5}};
    System.out.println(new Solution().solution(land));
  }

  private static class Solution {
    private int[][] land;

    /*
     * 1. land -> N행 4열
     * 2. 1행부터 항 행씩 내려올 때, 각 행의 4칸 중 한 칸만 밟으면서 내려올 수 있다.
     * 3. 같은 열을 반복해서 밟을 수 없다.
     */
    // 땅을 밟아가며, 최고 점수를 반환하게 할 것
    int solution(int[][] land) {
      this.land = land;
      int answer = 0;
      int rowLength = land.length;

      for (int row = 1; row < rowLength; row++) {
        for (int col = 0; col < 4; col++) land[row][col] += getMax(row, col);
      }
      for (int col = 0; col < 4; col++) answer = Math.max(answer, land[rowLength - 1][col]);

      return answer;
    }

    private int getMax(int row, int col) {
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < 4; i++) {
        if (i == col) continue;
        max = Math.max(max, land[row - 1][i]);
      }
      return max;
    }
  }
}
