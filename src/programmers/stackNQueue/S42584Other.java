package programmers.stackNQueue;

import java.util.Stack;

public class S42584Other {
  static class Solution {
    public int[] solution(int[] prices) {
      Stack<Integer> beginIdxs = new Stack<>();
      int i = 0;
      int[] terms = new int[prices.length];

      beginIdxs.push(i);
      for (i = 1; i < prices.length; i++) {
        while (!beginIdxs.empty() && prices[i] < prices[beginIdxs.peek()]) {
          int beginIdx = beginIdxs.pop();
          terms[beginIdx] = i - beginIdx;
        }
        beginIdxs.push(i);
      }
      while (!beginIdxs.empty()) {
        int beginIdx = beginIdxs.pop();
        terms[beginIdx] = i - beginIdx - 1;
      }

      return terms;
    }
  }

  public static void main(String[] args) {
    int[] prices = { 1, 2, 3, 4, 4, 4, 4, 3, 3 };
    for (int i : new Solution().solution(prices)) {
      System.out.print(i + " ,");
    }
  }
}
