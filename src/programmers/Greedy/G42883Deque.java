package programmers.greedy;

import java.util.ArrayDeque;
import java.util.Deque;

public class G42883Deque {
  static class Solution {
    public String solution(String number, int k) {
      char[] result = new char[number.length() - k];
      Deque<Character> deq = new ArrayDeque<>();
      for (int i = 0; i < number.length(); i++) {
        char c = number.charAt(i);
        while (!deq.isEmpty() && deq.peekLast() < c && k-- > 0) {
          deq.removeLast();
        }
        deq.addLast(c);
      }
      for (int i = 0; i < result.length; i++) {
        result[i] = deq.removeFirst();
      }
      return new String(result);
    }
  }

  public static void main(String[] args) {
    String number = "4177252841";
    int k = 4;
    System.out.println(new Solution().solution(number, k));
  }
}
