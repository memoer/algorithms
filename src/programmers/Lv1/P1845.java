package programmers.Lv1;

import java.util.HashSet;
import java.util.Set;

public class P1845 {
  static class Solution {
    public int solution(int[] nums) {
      Set<Integer> s = new HashSet<>();
      for (int num : nums) {
        s.add(num);
      }
      if (s.size() >= nums.length / 2) {
        return nums.length / 2;
      } else {
        return s.size();
      }
    }
  }

  public static void main(String[] args) {
    int[] nums = { 3, 1, 2, 3 };
    System.out.println(new Solution().solution(nums));
  }
}
