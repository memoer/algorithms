package temp.Search;

import java.util.Arrays;

public class BinaryS43238 {
  public static void main(String[] args) {
    int n = 6;
    int[] times = { 7, 10 };
    System.out.println(new Solution().solution(n, times));
  }

  static class Solution {
    public long solution(int n, int[] times) {
      long result = 0;
      Arrays.sort(times);
      long left = (long) 0L;
      long right = (long) times[times.length - 1] * n;
      while (left <= right) {
        long mid = (long) (left + right) / 2;
        long acc = 0;
        for (int time : times) {
          acc += (mid / time);
        }
        if (acc >= n) {
          result = mid;
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      }
      return result;
    }
  }
}
