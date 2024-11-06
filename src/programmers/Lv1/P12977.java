package programmers.Lv1;

public class P12977 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Solution().solution(nums));
    }

    static class Solution {

        public int solution(int[] nums) {
            int answer = 0;
            int len = nums.length;
            for (int i = 0; i < len; i++) {
                int a = nums[i];
                for (int j = i + 1; j < len; j++) {
                    int b = nums[j];
                    for (int k = j + 1; k < len; k++) {
                        int c = nums[k];
                        if (isPrime(a + b + c)) {
                            answer += 1;
                        }
                    }
                }
            }
            return answer;
        }

        private boolean isPrime(int n) {
            int sqrt = (int) Math.sqrt(n);
            for (int i = 2; i <= sqrt; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
