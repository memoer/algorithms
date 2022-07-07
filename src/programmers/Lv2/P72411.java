package programmers.Lv2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

// ! 코스 요리 메뉴는 최소 2가지 이상
// ! 최소 2명 이상의 손님으로부터 주문된 단품 메뉴 조합에 대해서만
//! 각 손님들이 주문할 때 가장 많이 함께 주문한 단품메뉴들
public class P72411 {
  static class Solution {
    private Map<String, Integer> map;
    private Map<Integer, Integer> max;

    public String[] solution(String[] orders, int[] courses) {
      map = new HashMap<>();
      max = new HashMap<>();
      for (String order : orders) {
        char[] chOrder = order.toCharArray();
        Arrays.sort(chOrder);
        for (int course : courses) combination(chOrder, new boolean[order.length()], course, 0);
      }
      for (Entry<String, Integer> entry : map.entrySet()) {
        int length = entry.getKey().length();
        max.put(length, !max.containsKey(length) ? entry.getValue() : Math.max(max.get(length), entry.getValue()));
      }
      return map.entrySet().stream()
              .filter(
                      p -> p.getValue() >= 2 && p.getValue().equals(max.get(p.getKey().length())))
              .map(Entry::getKey)
              .sorted(String::compareTo).toArray(String[]::new);
    }

    private void combination(char[] order, boolean[] checked, int course, int idx) {
      if (course == 0) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < order.length; i++) {
          if (checked[i]) sb.append(order[i]);
        }
        String key = sb.toString();
        map.put(key, map.getOrDefault(key, 0) + 1);
        return;
      }
      for (int i = idx; i < order.length; i++) {
        checked[i] = true;
        combination(order, checked, course - 1, i + 1);
        checked[i] = false;
      }
    }
  }

  public static void main(String[] args) {
    String[] o = {"ABCDE", "AB", "CDAB", "ABDE", "XABYZ", "ABXYZ", "ABCD", "ABCDE", "ABCDE", "ABCDE", "AB", "AB", "AB",
            "AB", "AB", "AB", "AB", "AB", "AB", "AB"};
    int[] c = {2};
    for (String s : new Solution().solution(o, c)) {
      System.out.print(s + ", ");
    }
    System.out.println();
  }

}
