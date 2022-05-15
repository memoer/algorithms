package flab;

import java.util.Arrays;

public class D20220417_1 {
  public static void main(String[] args) {
    int[] nums1 = {1, 2, 3, 0, 0, 0};
    int m = 3;
    int[] nums2 = {2, 5, 6};
    int n = 3;
    new Solution().merge(nums1, m, nums2, n);
  }

  static class Solution {
    private void print(int[] nums1) {
      for (int num : nums1) {
        System.out.print(num + ", ");
      }
      System.out.println();
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
      int idx = 0;
      final int LENGTH = nums1.length;
      for (;m < LENGTH; m++) {
        nums1[m] = nums2[idx++];
      }
      Arrays.sort(nums1);
    }
  }
}
