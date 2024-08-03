package programmers.highscore.hash;

import java.util.Arrays;
import java.util.stream.Collectors;

public class P42578v {

  public static void main(String[] args) {

  }

  class Solution {

    public int solution(String[][] clothes) {
      return Arrays.stream(clothes)
          .collect(Collectors.groupingBy(array -> array[1], Collectors.summingInt(e -> 1)))
          .values()
          .stream()
          .reduce(1, (acc, cur) -> acc * (cur + 1)) - 1;
    }
  }
}
