package programmers.Lv2;

public class P68936 {

  public static void main(String[] args) {
    int[][] arr = {{1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1},
        {0, 1, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 1, 0, 0, 1}, {0, 0, 0, 0, 1, 1, 1, 1}};
    for (int n : new Solution().solution(arr)) {
      System.out.print(n + ", ");
    }
    System.out.println();
  }

  private static class Solution {

    int[] answer;
    int[][] arr;

    /*
     * 1 <= arr.length <= 1,024
     * arr은 정사각형
     * 0 <= arr[i] <= 1
     */
    // return -> {0의 개수, 1의 개수}
    public int[] solution(int[][] arr) {
      answer = new int[2];
      this.arr = arr;
      permutation(0, 0, arr.length);
      return answer;
    }

    private void permutation(int startRow, int startCol, int length) {
      if (length == 1 || isAllSame(startRow, startCol, length)) {
        int number = arr[startRow][startCol];
        answer[number] += 1;
        return;
      }
      int nextLength = length / 2;
      // top left
      permutation(startRow, startCol, nextLength); // 0,0,2
      // top right
      permutation(startRow, startCol + nextLength, nextLength); // 0, 2, 2
      // bottom left
      permutation(startRow + nextLength, startCol, nextLength); // 2, 0, 0
      // bottom right
      permutation(startRow + nextLength, startCol + nextLength, nextLength); // 2, 2, 2
    }


    private boolean isAllSame(int startRow, int startCol, int length) {
      int number = arr[startRow][startCol];
      int rLength = startRow + length;
      int cLength = startCol + length;
      for (int row = startRow; row < rLength; row++) {
        for (int col = startCol; col < cLength; col++) {
          if (arr[row][col] != number) {
            return false;
          }
        }
      }
      return true;
    }
  }
}
