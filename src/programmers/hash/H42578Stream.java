package programmers.hash;

import java.util.Arrays;
import java.util.stream.Collectors;

public class H42578Stream {
  public static void main(String[] args) throws Exception {
    String[][] clothes = { { "crowmask", "face" }, { "bluesunglasses", "face" }, { "smoky_makeup", "face" } };
    System.out.println(solution(clothes));
  }

  public static int solution(String[][] clothes) {
    return Arrays.stream(clothes).collect(Collectors.groupingBy(c -> c[1], Collectors.counting())).values().stream()
        .collect(Collectors.reducing(1L, (pre, cur) -> pre * (cur + 1))).intValue() - 1;
  }
}
