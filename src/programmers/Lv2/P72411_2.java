package programmers.Lv2;

import java.util.*;

public class P72411_2 {
  public static void main(String[] args) {
    String[] o = {"ABCDE", "AB", "CDAB", "ABDE", "XABYZ", "ABXYZ", "ABCD", "ABCDE", "ABCDE", "ABCDE", "AB",
            "AB", "AB",
            "AB", "AB", "AB", "AB", "AB", "AB", "AB"};
    //    String[] o = {"XYZ", "XWY", "WXA"};
    //        String[] o = {"ABCD", "ABCD", "ABCD"};
    int[] c = {2};
    for (String s : new P72411_2.Solution().solution(o, c)) System.out.print(s + ", ");
    System.out.println();
  }

  /*
  1. 코스 요리 메뉴는 최소 2가지 이상의 단품메뉴로 구성된다.
  2. 이전에 각 손님들이 주문할 때 가장 많이 함께 주문한 단품메뉴들을 코스요리 메뉴로 구성한다.
  3. 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스 요리 메뉴 후보에 포함된다.
   */
  private static class Solution {
    private Map<String, Integer> map;
    private Map<Integer, Integer> max;

    /*
    orders
    * 2 <= orders <= 20
    * 2 <= orders[i] <= 10 [ 대문자로만 구성, 문자열에는 알파벳 중복 X ]
     */
    /*
    courses
    * 2 <= courses[i] <= 10 [ 오름차순 정렬 ]
    * 같은 값 중복 X
     */
    /*
    정답 -> 메뉴 구성을 문자열 형식으로 배열에 담아, 사전 순으로 오름차순 정렬
    * 배열의 각 원소에 저장된 문자열 또한 알파벳 오름차순
    * 가장 많이 함께 주문된 메뉴 구성이 여러 개면, 모두 배열에 담아 return
     */
    public String[] solution(String[] orders, int[] courses) {
      map = new HashMap<>();
      max = new HashMap<>();
      loopCombination(orders, courses);
      return peekAndSortByAlphabet();
    }

    // 20 * 10log10 -> 고려할 필요 없음. 매우 작다.
    private void loopCombination(String[] orders, int[] courses) {
      // orders 최대 크기 -> 20
      int length = orders.length;
      for (String order : orders) {
        char[] chars = order.toCharArray();
        Arrays.sort(chars);
        for (int course : courses) combination(new String(chars), course, new StringBuilder(), 0);
      }
    }

    private void combination(String order, int course, StringBuilder sb, int start) {
      if (course == 0) {
        String key = sb.toString();
        int count = map.getOrDefault(key, 0) + 1;
        map.put(key, count);
        max.put(key.length(), Math.max(count, max.getOrDefault(key.length(), 1)));
        return;
      }
      int sbLength = sb.length();
      int oLength = order.length();
      for (int i = start; i < oLength; i++) {
        sb.append(order.charAt(i));
        combination(order, course - 1, sb, i + 1);
        sb.delete(sbLength, sbLength + 1);
      }
    }

    private String[] peekAndSortByAlphabet() {
      List<String> list = new ArrayList<>();
      // 1. 최악 -> courses[i] 원소 2~10이 모두 들어가 있을경우
      // 2. 시간복잡도 고려할 필요 없음 -> 매우 작음
      for (String course : map.keySet()) {
        int count = map.get(course);
        if (count < 2) continue;
        if (max.get(course.length()) == count) list.add(course);
      }
      Collections.sort(list);
      return list.toArray(new String[0]);
    }
  }

}
