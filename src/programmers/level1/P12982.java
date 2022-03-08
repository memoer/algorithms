package programmers.level1;

import java.util.Arrays;

public class P12982 {
  static class Solution {
    public int solution(int[] d, int budget) {
      int answer = 0;
      Arrays.sort(d);
      for (int t : d) {
        if (budget - t >= 0) {
          answer += 1;
          budget -= t;
        } else {
          break;
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] d = { 2, 2, 3, 3 };
    int budget = 10;
    System.out.println(new Solution().solution(d, budget));
  }
}
