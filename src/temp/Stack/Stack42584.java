package temp.Stack;

import java.util.Stack;

public class Stack42584 {
  static class Solution {
    public int[] solution(int[] prices) {
      Stack<Integer> beginIdxs = new Stack<>();
      int priceLength = prices.length;
      int[] terms = new int[priceLength];
      int i = 0;

      beginIdxs.push(i);
      for (i = 1; i < priceLength; i++) {
        while (!beginIdxs.isEmpty() && prices[beginIdxs.peek()] > prices[i]) {
          int beginIdx = beginIdxs.pop();
          terms[beginIdx] = i - beginIdx;
        }
        beginIdxs.push(i);
      }
      while (!beginIdxs.isEmpty()) {
        int beginIdx = beginIdxs.pop();
        terms[beginIdx] = i - beginIdx - 1;
      }
      return terms;
    }
  }

  public static void main(String[] args) {
    int[] prices = { 1, 2, 3, 3, 2 };
    for (int term : new Solution().solution(prices)) {
      System.out.print(term + ",");
    }
    System.out.println();
  }
}
