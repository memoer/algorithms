package programmers.Lv2;

import java.util.Arrays;

public class P42746 {
  static class Solution {
    public String solution(int[] numbers) {
      String answer = String.join("",
          Arrays.stream(numbers).mapToObj(String::valueOf).sorted((pre, cur) -> (cur + pre).compareTo(pre + cur))
              .toArray(String[]::new));
      return answer.charAt(0) == '0' ? "0" : answer;
    }
  }

  public static void main(String[] args) {
    int[] numbers = { 0, 0, 0, 0 };
    System.out.println(new Solution().solution(numbers));
  }
}
