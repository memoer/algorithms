package temp.Sort;

import java.util.Arrays;

// 110 101
public class Sort42746 {
  static class Solution {
    public String solution(int[] numbers) {
      String[] strArr = Arrays.stream(numbers).mapToObj(n -> String.valueOf(n))
          .sorted((cur, next) -> (next + cur).compareTo(cur + next))
          .toArray(String[]::new);
      return strArr[0].equals("0") ? "0" : String.join("", strArr);
    }
  }

  public static void main(String[] args) {
    int[] numbers = { 1, 10, 100, 100, 10000 };
    System.out.println(new Solution().solution(numbers));
  }
}
// 1000
// 999
// 99
// 98
