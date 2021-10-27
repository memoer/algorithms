package programmers.hash;

import java.util.HashMap;

public class H42576Hash {
  public static void main(String[] args) throws Exception {
    String[] p = new String[] { "leo", "kiki", "eden" };
    String[] c = new String[] { "eden", "kiki" };
    String result = solution(p, c);
    System.out.println(result);
  }

  public static String solution(String[] participant, String[] completion) {
    String answer = "";
    HashMap<String, Integer> hm = new HashMap<>();
    for (String player : participant) {
      hm.put(player, hm.getOrDefault(player, 0) + 1);
    }
    for (String player : completion) {
      hm.put(player, hm.get(player) - 1);
    }
    for (String key : hm.keySet()) {
      if (hm.get(key) != 0) {
        answer = key;
        break;
      }
    }
    return answer;
  }
}
