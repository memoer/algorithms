package flab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class D20220410_2 {
  public static void main(String[] args) {
    int[] nums = {-1, 0, 1, 2, -1, 4};
    System.out.println(new Solution().threeSum(nums));
  }

  static class Solution {
    private int length;

    private int findPositiveIdx(int[] nums) {
      for (int i = 0; i < this.length; i++) {
        if (nums[i] > 0) return i;
      }
      return -1;
    }

    public List<List<Integer>> threeSum(int[] nums) {
      this.length = nums.length;
      if (length < 3) return Collections.emptyList();

      Arrays.sort(nums);
      int positiveIdx = this.findPositiveIdx(nums);
      if (positiveIdx == -1) {
        if (nums[0] == 0) return List.of(List.of(0, 0, 0));
        return Collections.emptyList();
      }

      List<List<Integer>> result = new ArrayList<>();
      for (int i = 0; i < positiveIdx; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;
        int left = i + 1;
        int right = length - 1;
        while (left < right) {
          int sum = nums[i] + nums[left] + nums[right];
          if (sum > 0) {
            right -= 1;
          } else if (sum < 0) {
            left += 1;
          } else {
            result.add(List.of(nums[i], nums[left++], nums[right--]));
            while (left < right && nums[left] == nums[left - 1]) left++;
            while (left < right && nums[right] == nums[right + 1]) right--;
          }
        }
      }

      return result;
    }
  }
}
