package programmers.Lv2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P64065 {
  static class Solution {
    public int[] solution(String s) {
      String[] arr = s.replaceAll("[{]", " ").replaceAll("[}]", " ").trim().split(" , ");
      Arrays.sort(arr, (pre, cur) -> pre.length() - cur.length());
      int[] answer = new int[arr.length];
      int idx = 0;
      Set<String> set = new HashSet<>();

      for (String s1 : arr) {
        for (String s2 : s1.split(",")) {
          // 중복된 값을 넣을 경우, false가 리턴
          // 새로운 값을 넣을 경우, true가 리턴
          if (set.add(s2)) {
            answer[idx++] = Integer.parseInt(s2);
          }
        }
      }

      return answer;
    }
  }

  public static void main(String[] args) {
    String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
    for (int num : new Solution().solution(s)) {
      System.out.print(num + ", ");
    }
    System.out.println();
  }
}
