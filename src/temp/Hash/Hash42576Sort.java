package temp.Hash;

import java.util.Arrays;

public class Hash42576Sort {
  static class Solution {
    public String solution(String[] participant, String[] completion) {
      Arrays.sort(participant);
      Arrays.sort(completion);
      for (int i = 0; i < completion.length; i++) {
        if (!participant[i].equals(completion[i])) {
          return participant[i];
        }
      }
      return participant[participant.length - 1];
    }
  }

  public static void main(String[] args) {
    String[] participant = { "leo", "kiki", "eden" };
    String[] completion = { "eden", "kiki" };
    System.out.println(new Solution().solution(participant, completion));
  }
}
