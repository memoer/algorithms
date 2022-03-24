package programmers.Lv2;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 1. 자카드 유사도 J(A,B) -> A,B와 교집합 / A,B의 합집합
 * Ex) A->{1,2,3}, B->{2,3,4} => 2/4 => J(A,B) = 0.5
 * 
 * 2. 확장 자카드 유사도 J(A,B)
 * Ex) A->{1,1,2,2,3}, B->{1,2,2,4,5}=>합집합->{1,1,2,2,3,4,5}, 교집합->{1,2,2}=>3/7
 * ...합집합할 경우, A는 1이 2개 / B는 1이 1개 -> 제일 큰 값 2개 -> 1,1 2개 넣는다.
 * 
 * Ex) FRANCE, FRENCH 2개씩 끊는다면,
 * ...A -> {FR, RA, AN, NC, CE}, B -> {FR, RE, EN, NC, CH}
 * ...합집합 -> {FR, RA, AN, NC, CE, RE, EN, CH}
 * ...교집합 -> {FR, NC}
 */
public class P17677 {
  static class Solution {
    private Map<String, Integer> mapA;
    private Map<String, Integer> mapB;

    private Map<String, Integer> getMap(String str) {
      int length = str.length();
      Map<String, Integer> map = new HashMap<>();
      for (int i = 0; i < length - 1; i++) {
        boolean isAvailable = true;
        String fragment = str.substring(i, i + 2);
        for (int j = 0; j < 2; j++) {
          if (fragment.charAt(j) < 97 || fragment.charAt(j) > 122) {
            isAvailable = false;
            break;
          }
        }
        if (isAvailable) {
          map.put(fragment, map.getOrDefault(fragment, 0) + 1);
        }
      }
      return map;
    }

    private int getUnion() {
      int cnt = 0;
      for (Entry<String, Integer> entryA : mapA.entrySet()) {
        if (mapB.containsKey(entryA.getKey())) {
          cnt += Math.max(mapB.get(entryA.getKey()), entryA.getValue());
          mapA.put(entryA.getKey(), 0);
          mapB.put(entryA.getKey(), 0);
        } else {
          cnt += entryA.getValue();
        }
      }
      for (int value : mapB.values()) {
        cnt += value;
      }
      return cnt;
    }

    private int getIntersection() {
      int cnt = 0;
      for (String a : mapA.keySet()) {
        for (String b : mapB.keySet()) {
          if (a.equals(b)) {
            cnt++;
            break;
          }
        }
      }
      return cnt;
    }

    public int solution(String strA, String strB) {
      mapA = getMap(strA.toLowerCase());
      mapB = getMap(strB.toLowerCase());
      if (mapA.isEmpty() && mapB.isEmpty()) {
        return 65536;
      }
      return (int) Math.floor((double) getIntersection() / getUnion() * 65536);
    }
  }

  public static void main(String[] args) {
    String str1 = "handshake";
    String str2 = "shake hands";
    System.out.println(new Solution().solution(str1, str2));
  }
}
