package temp.Search;

public class Search42842 {
  static class Solution {
    public int[] solution(int brown, int yellow) {
      int[] answer = new int[2];
      for (int height = 1;; height++) {
        int topBrownNumber = (brown - height * 2) / 2;
        int topYellowNumber = topBrownNumber - 2;
        if (topYellowNumber * height == yellow || topBrownNumber < height + 2) {
          answer[0] = topBrownNumber;
          answer[1] = height + 2;
          break;
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    for (int i : new Solution().solution(4_004, 999_999)) {
      System.out.println(i);
    }
  }
}
