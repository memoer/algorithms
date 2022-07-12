package leetcode;

import flab.D20220501_3;

// https://leetcode.com/problems/split-array-largest-sum/
public class P410 {
  public static void main(String[] args) {
    int[] nums = {7, 2, 5, 10, 8};
    int m = 2;

    System.out.println(new Solution().splitArray(nums, m));
  }

  private static class Solution {
    private int[] nums;
    private int length;
    private int m;

    // 1 <= nums.length <= 1,000
    // 1 <= m <= 50
    public int splitArray(int[] nums, int m) {
      this.nums = nums;
      this.length = nums.length;
      this.m = m;
      int answer = Integer.MAX_VALUE;
      int left = Integer.MIN_VALUE;
      int right = 0;
      for (int num : nums) {
        left = Math.max(left, num);
        right += num;
      }
      while (left <= right) {
        int mid = (left + right) / 2;
        if (isAvailable(mid)) {
          answer = Math.min(answer, mid);
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      }

      return answer;
    }

    private boolean isAvailable(int mid) {
      int splitted = 0;
      int idx = 0;
      while (idx < length) {
        int acc = 0;
        while (idx < this.length && acc + nums[idx] <= mid) acc += nums[idx++];
        splitted += 1;
      }
      // m보다 작다는 것은, 선정한 mid의 값이 너무 크다는 뜻이 된다.
      // 1. m이 5이고 splitted가 3이라면, 여러 원소들을 합쳐도 mid만큼 크지가 않아 3개로만 분할되었다는 뜻이 되고
      // 2. 이는 즉 mid의 값을 낮추어야 한다는 말로 된다.
      // 3. isAvailable의 함수가 true를 반환할 경우엔, "right = mid - 1" 을 통해 값을 낮추어야 한다.
      return splitted <= this.m;
    }
  }
}
