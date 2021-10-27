package programmers.hash;

import java.util.Arrays;

public class H42576Sort {
  public static void main(String[] args) throws Exception {
    String[] p = new String[] { "stanko", "mislav", "mislav", "ana" };
    String[] c = new String[] { "stanko", "ana", "mislav" };
    String result = solution(p, c);
    System.out.println(result);
  }

  public static String solution(String[] participant, String[] completion) {
    Arrays.sort(participant);
    Arrays.sort(completion);
    for (int i = 0; i < participant.length - 1; i++) {
      if (!participant[i].equals(completion[i])) {
        return participant[i];
      }
    }
    return participant[participant.length - 1];
  }
}
