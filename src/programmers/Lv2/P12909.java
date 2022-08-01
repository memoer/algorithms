package programmers.Lv2;

import java.util.ArrayDeque;
import java.util.Deque;

public class P12909 {
  public static void main(String[] args) {
    String[] s = {"()()", "(())()", ")()(", "(()("};
    for (String s1 : s) System.out.println(new Solution().solution(s1));
  }

  private static class Solution {
    boolean solution(String s) {
      char[] chars = s.toCharArray();
      Deque<Character> dq = new ArrayDeque<>();
      for (Character c : chars) {
        switch (c) {
          case '(':
            dq.push(c);
            break;
          case ')':
            if (dq.isEmpty()) return false;
            else if (dq.peek() == '(') dq.pop();
            break;
          default:
            throw new UnsupportedOperationException();
        }
      }
      return dq.isEmpty();
    }
  }
}
