package programmers.hash;

import java.util.HashMap;

public class H42578 {
  public static void main(String[] args) throws Exception {
    String[][] clothes = { { "crowmask", "face" }, { "bluesunglasses", "face" }, { "smoky_makeup", "face" } };
    System.out.println(solution(clothes));
  }

  public static int solution(String[][] clothes) {
    int answer = 1;
    HashMap<String, Integer> map = new HashMap<>();
    for (String[] cloth : clothes) {
      String type = cloth[1];
      map.put(type, map.getOrDefault(type, 1) + 1);
    }
    for (int num : map.values()) {
      answer *= num;
    }
    return answer - 1;
  }
}

// 1이 아닌 숫자들을 뽑고
// 1이 아닌 숫자들 * 1인 숫자들의 갯수
//