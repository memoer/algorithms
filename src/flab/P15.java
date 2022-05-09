package flab;

import java.util.*;

// https://leetcode.com/problems/3sum/
public class P15 {
  public static void main(String[] args) {
    int[] nums = {-1, 0, 1, 2, -1, 4};
    System.out.println(new Solution().threeSum(nums));
  }

  static class Solution {
    private int length;

    private int findPositiveIdx(int[] nums) {
      for (int i = 0; i < this.length; i++) {
        if (nums[i] > 0) {
          return i;
        }
      }
      return -1;
    }

    private List<List<Integer>> getAnswerOnNegative(int[] nums) {
      int count = 0;
      for (int i = 0; i < this.length; i++) {
        if (count == 3) {
          break;
        } else if (nums[i] == 0) {
          count += 1;
        }
      }
      return count == 3 ? List.of(List.of(0, 0, 0)) : Collections.emptyList();
    }

    public List<List<Integer>> threeSum(int[] nums) {
      this.length = nums.length;
      Arrays.sort(nums);
      int firstPositive = findPositiveIdx(nums);
      if (firstPositive == -1) {
        if (this.length < 3) {
          return Collections.emptyList();
        }
        return getAnswerOnNegative(nums);
      }
      List<List<Integer>> answer = new ArrayList<>();
      for (int i = 0; i < firstPositive; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;
        int left = i + 1;
        int right = this.length - 1;
        while (left < right) {
          int sum = nums[i] + nums[left] + nums[right];
          if (sum > 0) {
            right--;
          } else if (sum < 0) {
            left++;
          } else {
            answer.add(List.of(nums[i], nums[left], nums[right]));
            right--;
            left++;
            while (left < right && nums[right] == nums[right + 1]) right--;
            while (left < right && nums[left] == nums[left - 1]) left++;
          }
        }
      }
      return answer;
    }
  }
}
