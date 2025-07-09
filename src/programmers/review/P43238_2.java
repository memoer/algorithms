package programmers.review;

import java.util.Arrays;

public class P43238_2 {

    public static void main(String[] args) {
        long solution = new Solution().solution(6, new int[]{7, 10});
        System.out.println(solution);
    }

    private static class Solution {
        public long solution(int n, int[] times) {
            Arrays.sort(times);
            long l = 0;
            long r = (long) times[times.length - 1] * n;
            long result = 0;
            while (l <= r) {
                long mid = (l + r) / 2;
                long acc = 0;
                for (int time : times) {
                    acc += mid / time;
                }
                if (acc >= n) {
                   r= mid - 1;
                    result = mid;
                } else {
                    l = mid + 1;
                }
            }
            return result;
        }

    }
}
