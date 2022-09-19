package temp.kakao;

import java.util.HashMap;
import java.util.Map;

public class P17677 {
  /**
   * 두 집합 A, B 사이의 자카드 유사도 J(A, B)는 두 집합의 교집합 크기를 두 집합의 합집합 크기로 나눈 값으로 정의된다.
   * Ex) 집합 A = {1, 2, 3}, 집합 B = {2, 3, 4}라고 할 때
   * 1. 교집합 A ∩ B = {2, 3}, 합집합 A ∪ B = {1, 2, 3, 4}이 되므로, 집합 A, B 사이의 자카드 유사도 J(A, B) = 2/4 = 0.5가 된다.
   * 2. 집합 A와 집합 B가 모두 공집합일 경우에는 나눗셈이 정의되지 않으니 따로 J(A, B) = 1로 정의한다
   */
  private final int INTERSECTION = 0;
  private final int UNION = 1;

  /**
   * 2 <= str.length <= 1,000
   * 두 글자씩 끊는다. 영문자만 유효하다. 그 외 글자는 버린다.
   * 대/소문자 차이는 무시한다.
   */

  public static void main(String[] args) {
    String str1 = "E=M*C^2";
    String str2 = "e=m*c^2";
    System.out.println(new P17677().solution(str1, str2));
  }

  public int solution(String str1, String str2) {
    Map<String, int[]> map = getMap(str1, str2);
    if (map.size() == 0) return 65_536;
    int[] test = test(map);
    return (int) Math.floor((double) test[INTERSECTION] / test[UNION] * 65_536);
  }

  private int[] test(Map<String, int[]> map) {
    int[] ret = new int[2];
    for (Map.Entry<String, int[]> e : map.entrySet()) {
      int a = e.getValue()[0];
      int b = e.getValue()[1];
      ret[UNION] += Integer.max(a, b);
      if (a != 0 && b != 0) ret[INTERSECTION] += Integer.min(a, b);
    }
    return ret;
  }

  private Map<String, int[]> getMap(String str1, String str2) {
    Map<String, int[]> map = new HashMap<>();
    accumulate(map, str1, 0);
    accumulate(map, str2, 1);
    return map;
  }

  private void accumulate(Map<String, int[]> map, String str, int number) {
    int length = str.length() - 1;
    for (int i = 0; i < length; i++) {
      boolean skip = false;
      char[] chars = str.substring(i, i + 2).toLowerCase().toCharArray();
      for (char ch : chars) {
        if (Character.isLowerCase(ch)) continue;
        skip = true;
        break;
      }
      if (skip) continue;
      String target = new String(chars);
      if (!map.containsKey(target)) map.put(target, new int[2]);
      map.get(target)[number] += 1;
    }
  }
}
