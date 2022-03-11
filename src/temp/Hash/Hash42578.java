package temp.Hash;

import java.util.HashMap;
import java.util.Map;

public class Hash42578 {
  static class Solution {
    public int solution(String[][] clothes) {
      Map<String, Integer> map = new HashMap<>();
      for (String[] cloth : clothes) {
        String type = cloth[1];
        map.put(type, map.getOrDefault(type, 0) + 1);
      }
      return map.values().stream().reduce(1, (acc, cur) -> acc * (cur + 1)).intValue() - 1;
    }
  }

  public static void main(String[] args) {
    String[][] clothes = { { "yellowhat", "headgear" }, { "bluesunglasses", "eyewear" },
        { "green_turban", "headgear" } };
    System.out.println(new Solution().solution(clothes));
  }
}
