package programmers.review;

import java.util.Arrays;

public class R42746 {
  static class Solution {
    public String solution(int[] numbers) {
      StringBuilder sb = new StringBuilder();
      String[] answer = new String[numbers.length];
      boolean isZero = true;
      for (int i = 0; i < numbers.length; i++) {
        if (numbers[i] != 0) {
          isZero = false;
        }
        answer[i] = String.valueOf(numbers[i]);
      }
      if (isZero) {
        return "0";
      }
      Arrays.sort(answer, (pre, cur) -> (cur + pre).compareTo(pre + cur));
      for (String s : answer) {
        sb.append(s);
      }
      return sb.toString();
    }
  }

  public static void main(String[] args) {
    int[] numbers = { 0, 0, 0, 0, 0 };
    System.out.println(new Solution().solution(numbers));
  }
}
