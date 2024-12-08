package programmers.Lv1;

public class P77884_2 {

    public static void main(String[] args) {
        int l = 13;
        int r = 17;
        int solution = new Solution().solution(l, r);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int left, int right) {
            int answer = 0;

            for (int i = left; i <= right; i++) {
                int n = numOfDivisors(i);
                answer += (n % 2 == 0 ? i : -i);
            }

            return answer;
        }

        private int numOfDivisors(int num) {
            int size = 0;
            for (int i = 1; i <= num; i++) {
                if (num % i == 0) {
                    size += 1;
                }
            }
            return size;
        }

    }
}
