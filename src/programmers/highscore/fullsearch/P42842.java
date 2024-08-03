package programmers.highscore.fullsearch;

public class P42842 {

  public static void main(String[] args) {
//    1_003, 1_001
    for (int i : new Solution().solution(4_004, 999_999)) {
      System.out.printf("%d, ", i);
    }
    System.out.println();
// 4, 3
    for (int i : new Solution().solution(10, 2)) {
      System.out.printf("%d, ", i);
    }
  }

  private static class Solution {

    public int[] solution(int brown, int yellow) {
      int[] answer = new int[2];
      for (int yellowHeight = 1; ; yellowHeight++) {
        if (yellow % yellowHeight != 0) {
          continue;
        }

        int height = yellowHeight + 2;
        int width = yellow / yellowHeight + 2;
        if (height * width != brown + yellow) {
          continue;
        }

        answer[0] = width;
        answer[1] = height;
        break;
      }
      return answer;
    }

    public int[] other(int brown, int yellow) {
      int[] answer = new int[2];
      for (int yellowHeight = 1; ; yellowHeight++) {
        int numberOfBrownAtTop = (brown - yellowHeight * 2) / 2;
        int numberOfYellowAtTop = numberOfBrownAtTop - 2;
        if (numberOfYellowAtTop * yellowHeight == yellow || numberOfBrownAtTop < yellowHeight + 2) {
          answer[0] = numberOfBrownAtTop;
          answer[1] = yellowHeight + 2;
          break;
        }
      }
      return answer;
    }
  }
}
