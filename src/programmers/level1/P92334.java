package programmers.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class P92334 {
  public static void main(String[] args) {
    String[] idArr = { "muzi", "frodo", "apeach", "neo" };
    String[] reportArr = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
    int k = 2;

    for (int num : new Solution().solution(idArr, reportArr, k)) {
      System.out.print(num + ", ");
    }
    System.out.println();
  }

  static class Solution {
    public int[] solution(String[] idArr, String[] reportArr, int k) {
      int[] answer = new int[idArr.length];
      Map<String, Integer> idxMap = new HashMap<>();
      Map<String, List<String>> reportedMap = new HashMap<>();
      Set<String> set = new HashSet<>(Arrays.asList(reportArr));

      for (int i = 0; i < idArr.length; i++) {
        idxMap.put(idArr[i], i);
      }
      for (String s : set) {
        String[] splitted = s.split(" ");
        if (!reportedMap.containsKey(splitted[1])) {
          reportedMap.put(splitted[1], new ArrayList<>());
        }
        reportedMap.get(splitted[1]).add(splitted[0]);
      }
      for (String key : reportedMap.keySet()) {
        if (reportedMap.get(key).size() < k) {
          continue;
        }
        for (String report : reportedMap.get(key)) {
          answer[idxMap.get(report)] += 1;
        }
      }
      return answer;
    }
  }
}
