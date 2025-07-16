package programmers.review;

public class P12980 {

    public static void main(String[] args) {
        int solution = new Solution().solution(5_000);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int n) {
            if (n == 1) {
                return 1;
            }

            int result = 0;
            while (n != 0) {
                if (n % 2 == 0) {
                    n /= 2;
                } else {
                    n -= 1;
                    result += 1;
                }
            }
            return result;
        }

    }
}
