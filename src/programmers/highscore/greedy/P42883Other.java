package programmers.highscore.greedy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class P42883Other {

  public static void main(String[] args) {
    String number = "9876543214";
    int k = 4;
    String solution = new Solution().solution(number, k);
    System.out.println(solution);
  }

  public static class Solution {

//1119, 3
//
//1
//11
//111
//1119 3
//
//n + k-1 -> n + k - 1 -> n + k
    public String solution(String number, int k) {
      int len = number.length();
      char[] result = new char[len - k];
      Deque<Character> dq = new ArrayDeque<>();

      for (int i = 0; i < len; i++) {
        char c = number.charAt(i);
        while (!dq.isEmpty() && k > 0 && dq.peek() < c) {
          dq.pop();
          k -= 1;
        }
        dq.push(c);
      }

      for (int i = 0; i < result.length; i++) {
        result[i] = dq.pollLast();
      }

      return new String(result);
    }

  }
}