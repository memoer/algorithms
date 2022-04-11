package flab;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class P1 {
  static class Solution {
    public int[] solution(int[] heights) {
      final int LENGTH = heights.length;
      Deque<Integer> dq = new ArrayDeque<>();
      LinkedList<Integer> answer = new LinkedList<>();

      for (int i = 0; i < LENGTH - 1; i++) {
        if (heights[i] > heights[i + 1]) {
          dq.offerLast(heights[0]);
          answer.add(i);
        } else {
          while (!dq.isEmpty() && dq.peek() <= heights[i + 1]) {
            dq.pollLast();
            answer.removeLast();
          }
        }
      }
      answer.add(LENGTH - 1);

      return answer.stream().mapToInt(Integer::valueOf).toArray();
    }
  }

  public static void main(String[] args) {
    int[][] testCase = {
        { 4, 2, 3, 1 },
        { 4, 3, 2, 1 },
        { 1, 3, 2, 4 },
        { 2, 2, 2, 2 },
    };
    for (int[] heights : testCase) {
      for (int num : new Solution().solution(heights)) {
        System.out.print(num + ", ");
      }
      System.out.println();
    }
  }

}
