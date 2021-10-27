package programmers.review;

import java.util.Arrays;

public class P43238 {
  static class Solution {
    public long solution(int n, int[] times) {
      Arrays.sort(times);
      long answer = 0L;
      long right = (long) times[times.length - 1] * n;
      long left = 0;
      while (left <= right) {
        long mid = (left + right) / 2;
        int sum = 0;
        for (int t : times) {
          sum += mid / t;
        }
        if (sum < n) {
          left = mid + 1;
        } else {
          answer = mid;
          right = mid - 1;
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int n = 6;
    int[] times = { 7, 10 };
    System.out.println(new Solution().solution(n, times));
  }
}
