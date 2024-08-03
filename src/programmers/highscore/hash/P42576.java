package programmers.highscore.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class P42576 {

  public static void main(String[] args) {

  }

  class Solution{
    public String solution(String[] participant, String[] completion) {
      Map<String, Integer> map = new HashMap<>();
      for (String s : participant) {
        map.put(s, map.getOrDefault(s, 0) + 1);
      }
      for (String k : completion) {
        int v = map.get(k) - 1;
        map.put(k, v);
      }
      String result = null;
      for (Entry<String, Integer> entry : map.entrySet()) {
        if (entry.getValue() != 0) {
          result = entry.getKey();
        }
      }
      return result;
    }
  }
}
