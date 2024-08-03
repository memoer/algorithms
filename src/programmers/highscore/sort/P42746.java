package programmers.highscore.sort;

import java.util.Arrays;

public class P42746 {

  public static void main(String[] args) {
    int[] numbers = new int[]{3, 30, 34, 5, 9};
    String solution = new Solution().solution(numbers);
    System.out.println(solution);
  }

  private static class Solution {

    public String solution(int[] numbers) {
      String answer = Arrays.stream(numbers)
          .mapToObj(String::valueOf)
          .sorted((pre, cur) -> (pre + cur).compareTo(cur + pre))
          .reduce("", (acc, cur) -> cur + acc);
      if (answer.charAt(0) == '0') {
        return "0";
      } else {
        return answer;
      }
    }

  }
}