package programmers.Lv2;

import java.util.ArrayDeque;
import java.util.Deque;

public class P12973 {
  static class Solution {
    public int solution(String s) {
      Deque<Character> dq = new ArrayDeque<>(s.length());
      for (char c : s.toCharArray()) {
        if (dq.isEmpty() || dq.peek() != c) {
          dq.push(c);
        } else {
          dq.pop();
        }
      }
      return dq.isEmpty() ? 1 : 0;
    }
  }

  public static void main(String[] args) {
    String s = "abcda";
    System.out.println(new Solution().solution(s));
  }

}
