package programmers.highscore.binarysearch;

import java.util.Arrays;

public class P43238 {

    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};
        long solution = new Solution().solution(n, times);
        System.out.println(solution);
    }

    private static class Solution {

        public long solution(int n, int[] times) {
            Arrays.sort(times);

            long answer = 0;
            long l = 0;
            long r = (long) n * times[times.length - 1];

            while (l <= r) {
                long mid = (l + r) / 2;
                long sum = 0;
                for (int time : times) {
                    sum += (mid / time);
                }
                if (sum >= n) {
                    r = mid - 1;
                    answer = mid;
                } else {
                    l = mid + 1;
                }
            }

            return answer;
        }

    }
}
