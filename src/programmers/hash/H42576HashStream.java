package programmers.hash;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class H42576HashStream {
  public static void main(String[] args) throws Exception {
    String[] p = new String[] { "leo", "kiki", "eden" };
    String[] c = new String[] { "eden", "kiki" };
    String result = solution(p, c);
    System.out.println(result);
  }

  public static String solution(String[] participant, String[] completion) {
    Map<String, Long> pMap = Arrays.asList(participant).stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    for (String player : completion) {
      Long value = pMap.get(player) - 1L;
      if (value != 0L) {
        pMap.put(player, value);
      } else {
        pMap.remove(player);
      }
    }
    return pMap.keySet().iterator().next();
  }
}
