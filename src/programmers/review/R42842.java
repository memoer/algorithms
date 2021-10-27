package programmers.review;

public class R42842 {
  static class Solution {
    public int[] solution(int brown, int yellow) {
      int[] answer = new int[2];
      for (int row = 1; row <= yellow; row++) {
        if (yellow % row != 0) {
          continue;
        }
        int col = yellow / row + 2;
        int total = col * 2 + yellow + row * 2;
        if (total == brown + yellow) {
          answer[0] = col;
          answer[1] = row + 2;
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
  }
}
