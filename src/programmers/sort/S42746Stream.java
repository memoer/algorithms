package programmers.sort;

import java.util.Arrays;

public class S42746Stream {
  static class Solution {
    public String solution(int[] numbers) {
      String[] strArr = Arrays.stream(numbers).mapToObj(n -> String.valueOf(n))
          .sorted((cur, next) -> (next + cur).compareTo(cur + next))
          .toArray(String[]::new);
      return strArr[0].equals("0") ? "0" : String.join("", strArr);
    }
  }

  public static void main(String[] args) {

  }

}
