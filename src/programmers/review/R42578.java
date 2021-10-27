package programmers.review;

import java.util.HashMap;
import java.util.Map;

public class R42578 {
  static class Solution {
    public int solution(String[][] clothes) {
      int answer = 1;
      Map<String, Integer> map = new HashMap<>();
      for (int i = 0; i < clothes.length; i++) {
        String type = clothes[i][1];
        map.put(type, map.getOrDefault(type, 1) + 1);
      }
      for (String key : map.keySet()) {
        answer *= map.get(key);
      }
      return answer - 1;
    }
  }

  public static void main(String[] args) {
    String[][] clothes = { { "yellowhat", "headgear" }, { "bluesunglasses", "eyewear" },
        { "green_turban", "headgear" } };
    System.out.println(new Solution().solution(clothes));
  }
}
