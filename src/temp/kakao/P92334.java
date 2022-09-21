package temp.kakao;

import java.util.*;

public class P92334 {
  public static void main(String[] args) {
    String[] idArr = {"muzi", "frodo", "apeach", "neo"};
    String[] reportArr = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
    int k = 2;
    for(int n : new P92334().solution(idArr, reportArr, k)) System.out.print(n+", ");
    System.out.println();
  }
  /**
   * 한 번에 한 명 유저 신고 -> 횟수 제한 X, 동일한 유저에 대한 신고 횟수는 1회로 처리
   * K번 이상 신고당한 경우, 게시판 이용 정지
   */
  /**
   * id_list -> 이용자의 ID가 담긴 문자열 배열
   * report -> 각 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열
   * k -> 정지 기준이 되는 신고 횟수
   */
  // 신고 당한 유저가 정지 됐을 경우, 신고를 한 유저는 처리되었다는 메일을 받는다. 이를 총 몇 번 받았는지 반환
  public int[] solution(String[] idArr, String[] reportArr, int k) {
    Map<String, Integer> cntMap = new HashMap<>();
    Map<String, Set<String>> map = new HashMap<>();
    for (String r : reportArr) {
      String[] split = r.split(" ");
      String a = split[0];
      String b = split[1];
      if (!map.containsKey(a)) map.put(a, new HashSet<>());
      map.get(a).add(b);
    }
    for (Set<String> set : map.values()) for (String s : set) cntMap.put(s, cntMap.getOrDefault(s, 0) + 1);
    return Arrays.stream(idArr)
            .mapToInt(id -> {
              if (map.get(id) == null) return 0;
              int sum = 0;
              for (String p : map.get(id)) if (cntMap.get(p) >= k) sum += 1;
              return sum;
            }).toArray();
  }
}
