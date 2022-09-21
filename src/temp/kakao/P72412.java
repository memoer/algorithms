package temp.kakao;

import java.util.*;

public class P72412 {
  /**
   * 1 <= info.length <= 50,000
   * 1 <= query.length <= 100,000
   */
  /**
   * info -> 4가지의 정보와 획득한 코딩테스트 점수를 하나의 문자열로 구성한 값의 배열
   * query -> 개발팀이 궁금해하는 문의조건이 문자열 형태로 담긴 배열
   * <p>
   * [조건]을 만족하는 사람 중 코딩테스트 점수를 X점 이상 받은 사람은 모두 몇 명인가?
   */

  Map<String, List<Integer>> map;
  int t;

  public static void main(String[] args) {
    String[] info = new String[]{
            "java backend junior pizza 150",
            "python frontend senior chicken 210",
            "python frontend senior chicken 150",
            "cpp backend senior pizza 260",
            "java backend junior chicken 80",
            "python backend senior chicken 50"
    };
    String[] query = new String[]{
            "java and backend and junior and pizza 100",
            "python and frontend and senior and chicken 200",
            "cpp and - and senior and pizza 250",
            "- and backend and senior and - 150",
            "- and - and - and chicken 100",
            "- and - and - and - 150"
    };
    for (int n : new P72412().solution(info, query)) System.out.print(n + ", ");
    System.out.println();
  }

  public int[] solution(String[] info, String[] query) {
    map = new HashMap<>();
    int len = query.length;
    int[] ans = new int[len];
    // O(50,000 * 16)
    for (String s : info) combination(s.split(" "));
    Set<String> sorted = new HashSet<>();
    // O(100,000)
    for (int i = 0; i < len; i++) {
      String[] c = convert(query[i]);
      List<Integer> list = map.get(c[0]);
      if (list == null) continue;
      // info가 조건이 모두 같고 점수만 같다고 친다면, list의 길이는 50,000
      // O( 50,000 * log(50,000) )
      if (!sorted.contains(c[0])) {
        Collections.sort(list);
        sorted.add(c[0]);
      }
      // 이분탐색 -> O( log(50,000) )
      ans[i] = list.size() - search(list, Integer.parseInt(c[1]));
    }
    return ans;
  }

  private void combination(String[] split) {
    final String ALL = "-";
    String[] a = new String[]{split[0], ALL};
    String[] b = new String[]{split[1], ALL};
    String[] c = new String[]{split[2], ALL};
    String[] d = new String[]{split[3], ALL};
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        for (int k = 0; k < 2; k++) {
          for (int m = 0; m < 2; m++) {
            String key = a[i] + b[j] + c[k] + d[m];
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(Integer.parseInt(split[4]));
          }
        }
      }
    }
  }

  private String[] convert(String s) {
    StringBuilder sb = new StringBuilder();
    String[] split = s.split(" and ");
    String[] last = split[3].split(" ");
    String condition = sb.append(split[0]).append(split[1]).append(split[2]).append(last[0]).toString();
    return new String[]{condition, last[1]};
  }

  private int search(List<Integer> list, int target) {
    int left = 0;
    int right = list.size() - 1;
    while (left <= right) {
      int mid = (left + right) / 2;
      if (target <= list.get(mid)) right = mid - 1;
      else left = mid + 1;
    }
    return left;
  }


  private void combination(String[] s, StringBuilder sb, int depth) {
    if (depth == 4) {
      String key = sb.toString();
      if (!map.containsKey(key)) map.put(key, new ArrayList<>());
      map.get(key).add(Integer.parseInt(s[4]));
      return;
    }
    int len = sb.length();
    sb.append(s[depth]);
    combination(s, sb, depth + 1);
    sb.delete(len, len + s[depth].length());
    sb.append("-");
    combination(s, sb, depth + 1);
    sb.deleteCharAt(len);
  }
}
