package programmers.Lv1;

import java.time.LocalDate;

public class P12901 {
  static class Solution {

    public String solution(int a, int b) {
      LocalDate date = LocalDate.of(2016, a, b);
      return date.getDayOfWeek().toString().substring(0, 3);
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().solution(5, 24));
  }
}
