package programmers.highscore.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class P12909 {

  public static void main(String[] args) {
    new Solution().solution("()()");
  }

  private static class Solution {

    boolean solution(String s) {
      String[] split = s.split("");
      Deque<String> dq = new ArrayDeque<>();
      for (String str : split) {
        if (str.equals("(")) {
          dq.push(str);
        } else {
          if (dq.isEmpty()) {
            return false;
          } else {
            dq.pop();
          }
        }
      }
      return dq.isEmpty();
    }
  }
}
