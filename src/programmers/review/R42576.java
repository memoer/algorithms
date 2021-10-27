package programmers.review;

import java.util.HashMap;

public class R42576 {
  static class Solution {
    public String solution(String[] participant, String[] completion) {
      String answer = "";
      HashMap<String, Integer> pMap = new HashMap<>();
      for (String p : participant) {
        pMap.put(p, pMap.getOrDefault(p, 0) + 1);
      }
      for (String c : completion) {
        pMap.put(c, pMap.get(c) - 1);
      }
      for (String k : pMap.keySet()) {
        if (pMap.get(k) != 0) {
          answer = k;
          break;
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    String[] participant;
    String[] completion;
    new Solution().solution(participant, completion);
  }
}
