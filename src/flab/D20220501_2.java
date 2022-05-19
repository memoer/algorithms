package flab;


// https://leetcode.com/problems/koko-eating-bananas/
// - 시간당 k의 바나나를 먹을 수 있다.
// - k의 바나나보다 개수가 작으면, 해당 바나나를 모두 다 먹고, 그 시간대에는 다른 바나나를 먹지 못한다.
// - 최대한 천천히 먹고 싶다.
// 경비원이 비운 시간 h이 주어졌을 때, 코코가 제일 천천히 바나나를 모두 먹을 수 있는 k 를 반환하시오.
public class D20220501_2 {
  public static void main(String[] args) {
    int[] piles = {312_884_470};
    int h = 312_884_469;
    System.out.println(new Solution().minEatingSpeed(piles, h));
  }

  static class Solution {
    private int[] piles;

    private boolean isAvailable(int mid, int h) {
      int passedHours = 0;
      for (int pile : piles) {
        passedHours += (pile / mid) + (pile % mid == 0 ? 0 : 1);
        if(passedHours > h) return false;
      }
      return true;
    }

    public int minEatingSpeed(int[] piles, int h) {
      this.piles = piles;
      int answer = Integer.MAX_VALUE;

      long sum = 0;
      int max = Integer.MIN_VALUE;
      for (int pile : this.piles) {
        max = Math.max(pile, max);
        sum += pile;
      }

      int left = 1;
      int right = max;
      while (left <= right) {
        int mid = (left + right) / 2;
        if ((long) mid * h < sum || !this.isAvailable(mid, h)) {
          // 아예 불가능함
          left = mid + 1;
        } else {
          answer = Math.min(answer, mid);
          right = mid - 1;
        }
      }

      return answer;
    }
  }
}
