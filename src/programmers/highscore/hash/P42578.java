package programmers.highscore.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class P42578 {

  public static void main(String[] args) {
    String[][] strings = {
        {"yellow_hat", "headgear"},
        {"blue_sunglasses", "eyewear"},
        {"green_turban", "headgear"}
    };
    int solution = new Solution().solution(strings);
    System.out.println(solution);
  }

  private static class Solution {

    private Map<String, List<String>> map = new HashMap<>();
    private List<String> typeList = new ArrayList<>();
    private int numberOfType = 0;
    private int result = 0;

    public int solution(String[][] clothes) {
//      result = clothes.length;
//      initialize(clothes);
//      for (int i = 2; i <= numberOfType; i++) {
//        select(0, i, 0);
//      }
//      return result;

      return test(clothes);
    }

    private int test(String[][] clothes) {
      Map<String, Long> collect = Arrays.stream(clothes)
          .collect(Collectors.groupingBy(p -> p[1], Collectors.mapping(p -> p[0], Collectors.counting())));
      for (Entry<String, Long> entry : collect.entrySet()) {
        System.out.printf("%s, %d\n", entry.getKey(), entry.getValue());
      }
      return collect
          .values()
          .stream()
          .reduce(100L, (acc, value) -> {
            System.out.printf("%d, %d\n", acc, value);
            return acc * (value + 1);
          })
          .intValue() - 1;
    }

    private void initialize(String[][] clothes) {
      for (String[] clothe : clothes) {
        String name = clothe[0];
        String type = clothe[1];
        if (!map.containsKey(type)) {
          typeList.add(type);
          map.put(type, new ArrayList<>());
        }
        map.get(type).add(name);
      }
      numberOfType = typeList.size();
    }

    private void select(int cur, int limit, int start) {
      if (cur == limit) {
        result += 1;
        return;
      }

      for (; start < numberOfType; start++) {
        String type = typeList.get(start);
        for (String cloth : map.get(type)) {
          select(cur + 1, limit, start + 1);
        }
      }
    }
  }
}
