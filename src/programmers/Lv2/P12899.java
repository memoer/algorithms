package programmers.Lv2;

import java.util.ArrayDeque;
import java.util.Deque;

public class P12899 {
  static class Solution {
    public String solution(int n) {
      Deque<Integer> dq = new ArrayDeque<>();
      StringBuilder sb = new StringBuilder();
      while (n != 0) {
        int mod = n % 3;
        if (mod == 0) {
          dq.offer(3);
          n = (n / 3) - 1;
        } else {
          dq.offerLast(mod);
          n = n / 3;
        }
      }
      while (!dq.isEmpty()) {
        int num = dq.pollLast();
        if (num == 3) {
          sb.append(4);
        } else {
          sb.append(num);
        }
      }
      return sb.toString();
    }
  }

  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    for (int n : arr) {
      System.out.println(new Solution().solution(n));
    }
  }

}
