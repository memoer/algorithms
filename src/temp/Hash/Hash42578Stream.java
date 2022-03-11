package temp.Hash;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Hash42578Stream {
  static class Solution {
    public int solution(String[][] clothes) {
      return Arrays.asList(clothes).stream().collect(Collectors.groupingBy(c -> c[1], Collectors.counting())).values()
          .stream().reduce(1L, (acc, cur) -> acc * (cur + 1)).intValue() - 1;
    }
  }

  public static void main(String[] args) {
    String[][] clothes = { { "yellowhat", "headgear" }, { "bluesunglasses", "eyewear" },
        { "green_turban", "headgear" } };
    System.out.println(new Solution().solution(clothes));
  }
}
