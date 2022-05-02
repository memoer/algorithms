package programmers.Lv2;

import java.util.ArrayDeque;
import java.util.Deque;

public class P76502 {
  public static void main(String[] args) {
    String s = "[](){}";
    System.out.println(new Solution().solution(s));
  }

  static class Solution {
    private int sLength;

    public int solution(String s) {
      this.sLength = s.length();
      int answer = 0;
      for (int i = 0; i < sLength - 1; i++) {
        Deque<Character> dq = new ArrayDeque<>();
        String str = i == 0 ? s : getRotationedStrBy(i, s);
        for (int j = 0; j < sLength; j++) {
          char ch = str.charAt(j);
          if (dq.isEmpty()) {
            dq.push(ch);
          } else if (isPair(ch, dq.peek())) {
            dq.pop();
          } else {
            dq.push(ch);
          }
        }
        if (dq.isEmpty()) {
          answer += 1;
        }
      }
      return answer;
    }

    private String getRotationedStrBy(int start, String src) {
      return src.substring(start, this.sLength) + src.substring(0, start);
    }

    private boolean isPair(char ch, char poppedCh) {
      return switch (ch) {
        case ')' -> poppedCh == '(';
        case ']' -> poppedCh == '[';
        case '}' -> poppedCh == '{';
        default -> false;
      };
    }
  }
}
