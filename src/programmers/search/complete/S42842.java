package programmers.search.complete;

public class S42842 {
  static class Solution {
    public int[] solution(int brown, int yellow) {
      int[] answer = new int[2];
      for (int row = 1; row <= yellow; row++) {
        if (yellow % row != 0) {
          continue;
        }
        int sum = yellow / row * 2 + row * 2 + 4;
        int verticalNum = row + 2;
        int horizontalNum = yellow / verticalNum;
        if (sum == brown && horizontalNum >= verticalNum) {
          answer[0] = horizontalNum;
          answer[1] = verticalNum;
          break;
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    for (int i : new Solution().solution(10, 2)) {
      System.out.println(i);
    }
    ;
  }
}
