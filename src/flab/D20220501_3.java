package flab;

// [410] https://leetcode.com/problems/split-array-largest-sum/
public class D20220501_3 {
  public static void main(String[] args) {
    int[] nums = {7, 2, 5, 10, 8};
    int m = 2;

    System.out.println(new Solution().splitArray(nums, m));
  }

  static class Solution {
    private int[] nums;
    private int m;

    public int splitArray(int[] nums, int m) {
      this.nums = nums;
      this.m = m;

      int left = 0, right = 0, answer = Integer.MAX_VALUE;
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
      int splitted = 0, i = 0;
      while (i < this.nums.length) {
        int acc = 0;
        while (i < this.nums.length && nums[i] + acc <= mid) acc += nums[i++];
        splitted += 1;
      }
      return splitted <= this.m;
    }
  }
}


// 1, 3, 6, 10, 15
// 1,3,6 / 4, 9
