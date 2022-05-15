package flab;


public class D20220424_1 {
  public static void main(String[] args) {
    int[] nums = {1, 1, 2};
    System.out.println(new Solution().removeDuplicates(nums));
  }

  static class Solution {
    public int removeDuplicates(int[] nums) {
      final int LENGTH = nums.length;
      int result = 0;
      int pre = Integer.MIN_VALUE;
      int idx = 0;

      for (int i = 0; i < LENGTH; i++) {
        if (nums[i] == pre) continue;
        result += 1;
        pre = nums[i];
        nums[idx++] = pre;
      }
      return result;
    }
  }
}
