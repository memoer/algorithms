package programmers.highscore.stackqueue;

public class P42584 {

  public static void main(String[] args) {
//    4, 5, 1, 2, 6, 1, 1 -> 2,1,4,2,1,1,0
    int[] prices = new int[]{1, 2, 3, 2, 3};
    int[] solution = new Solution().solution(prices);

    for (int i : solution) {
      System.out.printf("%d, ", i);
    }
    System.out.println();
  }

  private static class Solution {

    public int[] solution(int[] prices) {
      int len = prices.length;
      int[] answer = new int[len];
      for (int i = 0; i < len - 1; i++) {
        for (int j = i + 1; j < len; j++) {
          answer[i] += 1;
          if (prices[i] > prices[j]) {
            break;
          }
        }
      }
      return answer;
    }
  }
//  0,1,2,3,4,
//  4-0
//  4-1
//  4-2
//  4-3
//  4-3
}
