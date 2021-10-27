package programmers.search.binary;

import java.util.Arrays;

public class S43238 {
  static class Solution {
    public long solution(int n, int[] times) {
      Arrays.sort(times);
      long answer = 0;
      long left = 0;
      long right = (long) times[times.length - 1] * n;

      while (left <= right) {
        long mid = (right + left) / 2;
        long sum = 0;
        for (int i = 0; i < times.length; i++) {
          sum += mid / times[i];
        }
        if (sum < n) {
          left = mid + 1;
        } else {
          // target n 보다 더 많은 수를 처리할 수 있음 -> 널널 -> 시간을 줄여야 함 -> 시간을 줄이면, 당연히 다음 sum 도 작아짐
          right = mid - 1;
          // sum>n -> target n 보다 이미 많은 수를 처리할 수 있음 -> 가능
          // 하지만, 제일 작은 값[최솟값]을 구해야 하기 때문에, break 하지 않음
          answer = mid;
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
