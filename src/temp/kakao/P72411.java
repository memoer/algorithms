package temp.kakao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P72411 {
  /**
   * 코스요리 메뉴는 최소 2가지 이상의 단품메뉴로 구성
   * 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리 메뉴 후보
   */
  private Map<String, Integer> cntMap;
  /**
   * orders -> 각 손님들이 주문한 단품메뉴들
   * courses -> 추가하고 싶은 코스요리 메뉴 [단품메뉴의 개수]
   */
  /**
   * 2 <= orders.length <= 20
   * 2 <= orders[i].length <= 10 [대문자만, 중복 X]
   * 1 <= courses.length <= 10
   * 2 <= courses[i] <= 10 [오름차순]
   */
  // 각 코스요리당 가장 많이 팔린 것을 반환한다. 코스요리에서 팔린 개수가 동일할 경우, 모두 반환한다.
  private int[] max;

  public static void main(String[] args) {
    String[] orders = new String[]{"XYZ", "XWY", "WXA"};
    int[] courses = new int[]{2, 3, 4};
    for (String s : new P72411().solution(orders, courses)) System.out.print(s + ", ");
    System.out.println();
  }

  public String[] solution(String[] orders, int[] courses) {
    cntMap = new HashMap<>();
    max = new int[11];

    for (String order : orders) {
      int limit = order.length();
      String s = sortedOrder(order);
      for (int course : courses) combination(s, course, 0, limit, new StringBuilder());
    }
    return cntMap.entrySet().stream()
            .filter(e -> e.getValue() >= 2 && max[e.getKey().length()] == e.getValue())
            .map(Map.Entry::getKey)
            .sorted(String::compareTo)
            .toArray(String[]::new);
  }

  private void combination(String order, int course, int start, int limit, StringBuilder sb) {
    int len = sb.length();
    if (len == course) {
      String candidate = sb.toString();
      int cnt = cntMap.getOrDefault(candidate, 0) + 1;
      cntMap.put(candidate, cnt);
      max[course] = Integer.max(max[course], cnt);
      return;
    }
    for (int i = start; i < limit; i++) {
      sb.append(order.charAt(i));
      combination(order, course, i + 1, limit, sb);
      sb.deleteCharAt(len);
    }
  }

  private String sortedOrder(String order) {
    char[] chars = order.toCharArray();
    Arrays.sort(chars);
    return new String(chars);
  }
}
