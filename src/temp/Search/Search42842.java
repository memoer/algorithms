package temp.Search;

public class Search42842 {
  static class Solution {
    public int[] solution(int brown, int yellow) {
      int[] answer = new int[2];
      for (int col = 1;; col++) {
        int topBrownNumber = (brown - col * 2) / 2;
        int topYellowNumber = topBrownNumber - 2;
        if (topYellowNumber * col == yellow || topBrownNumber < col + 2) {
          answer[0] = topBrownNumber;
          answer[1] = col + 2;
          break;
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    for (int i : new Solution().solution(24, 24)) {
      System.out.println(i);
    }
  }
}
