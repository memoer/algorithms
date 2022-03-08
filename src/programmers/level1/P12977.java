package programmers.level1;

import java.util.ArrayList;
import java.util.List;

public class P12977 {
  static class Solution {
    private boolean isPrime(int num) {
      for (int i = 2; i <= Math.sqrt(num); i++) {
        if (num % i == 0) {
          return false;
        }
      }
      return true;
    }

    public int solution(int[] nums) {
      int answer = 0;
      int length = nums.length;
      List<Integer> list = new ArrayList<>();
      for (int i = 0; i < length - 2; i++) {
        for (int j = i + 1; j < length - 1; j++) {
          for (int k = j + 1; k < length; k++) {
            list.add(nums[i] + nums[j] + nums[k]);
          }
        }
      }
      for (int candidate : list) {
        if (isPrime(candidate)) {
          answer += 1;
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] nums = { 1, 2, 3 };
    System.out.println(new Solution().solution(nums));
  }
}
