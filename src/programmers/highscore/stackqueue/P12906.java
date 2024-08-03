package programmers.highscore.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class P12906 {

  public static void main(String[] args) {
    int[] arr = new int[]{4, 4, 4, 3, 3};
    int[] solution = new Solution().solution(arr);

    for (int i : solution) {
      System.out.printf("%d, ", i);
    }
    System.out.println();
  }

  private static class Solution {

    public int[] solution(int[] arr) {
      Deque<Integer> dq = new ArrayDeque<>();
      for (int v : arr) {
        if (dq.isEmpty()) {
          dq.push(v);
        } else if (dq.peek() != v) {
          dq.push(v);
        }
      }

      int n = dq.size();
      int[] result = new int[n];
      for (int i = 0; i < n; i++) {
        result[i] = dq.pollLast();
      }
      return result;
    }
  }

}
