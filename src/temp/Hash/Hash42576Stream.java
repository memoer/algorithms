package temp.Hash;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Hash42576Stream {
  static class Solution {
    public String solution(String[] participant, String[] completion) {
      Map<String, Long> map = Arrays.asList(participant).stream()
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
      for (String key : completion) {
        Long num = map.get(key) - 1L;
        if (num == 0) {
          map.remove(key);
        }
      }
      return map.keySet().iterator().next();
    }
  }

  public static void main(String[] args) {
    String[] participant = { "leo", "kiki", "eden" };
    String[] completion = { "eden", "kiki" };
    System.out.println(new Solution().solution(participant, completion));
  }
}
