package programmers.Lv3;

import java.util.Arrays;

public class P43238 {
  public static void main(String[] args) {
    int n = 6;
    int[] times = new int[]{7, 10};
    System.out.println(new Solution().solution(n, times));
  }

  private static class Solution {
    // 입국심사를 기다리는 사람 수 n
    // 각 심사관이 한 명을 심사하는데 걸리는 시간이 담긴 배열 times
    public long solution(int n, int[] times) {
      Arrays.sort(times);

      long right = (long) times[times.length - 1] * n;
      long left = 0L;
      long answer = 0L;
      while (left <= right) {
        long mid = (left + right) / 2;
        long sum = 0;
        for (int time : times) sum += mid / time;
        if (sum >= n) {
          right = mid - 1;
          answer = mid;
        } else {
          left = mid + 1;
        }
      }
      return answer;
    }
  }
}
