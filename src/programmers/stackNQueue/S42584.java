package programmers.stackNQueue;

public class S42584 {
  static class Solution {
    public int[] solution(int[] prices) {
      int[] answer = new int[prices.length];
      for (int i = 0; i < prices.length - 1; i++) {
        for (int j = i + 1; j < prices.length; j++) {
          answer[i]++;
          if (prices[i] > prices[j]) {
            break;
          }
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] prices = { 1, 2, 3, 3, 2 };
    for (int i : new Solution().solution(prices)) {
      System.out.print(i + " ,");
    }
  }
}
