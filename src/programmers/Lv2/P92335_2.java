package programmers.Lv2;

import java.util.Arrays;

public class P92335_2 {

    public static void main(String[] args) {
        int solution = new Solution().solution(437674, 3);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int n, int k) {
            int answer = 0;
            long[] arr = Arrays.stream(Integer.toString(n, k).split("0")).filter(s -> !s.isEmpty())
                .mapToLong(Long::parseLong)
                .toArray();
            for (long i : arr) {
                if (isPrime(i)) {
                    answer += 1;
                }
            }
            return answer;
        }


        private boolean isPrime(long n) {
            if (n == 1) {
                return false;
            }
            int end = (int) Math.sqrt(n);
            for (int i = 2; i <= end; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }

    }
}
