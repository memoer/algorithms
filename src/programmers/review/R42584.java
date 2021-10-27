package programmers.review;

import java.util.Stack;

public class R42584 {
  static class Solution {
    public int[] solution(int[] prices) {
      int[] answer = new int[prices.length];
      Stack<Integer> beginIdxs = new Stack<>();
      beginIdxs.push(0);
      for (int i = 1; i < prices.length; i++) {
        while (!beginIdxs.isEmpty() && prices[i] < prices[beginIdxs.peek()]) {
          int beginIdx = beginIdxs.pop();
          answer[beginIdx] = i - beginIdx;
        }
        beginIdxs.push(i);
      }
      while (!beginIdxs.isEmpty()) {
        int i = beginIdxs.pop();
        answer[i] = prices.length - i - 1;
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] prices = { 1, 2, 3, 2, 3 };
    for (int i : new Solution().solution(prices)) {
      System.out.print(i + ", ");
    }
  }
}
