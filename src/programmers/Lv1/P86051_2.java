package programmers.Lv1;

public class P86051_2 {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 6, 7, 8, 0};
        int solution = new Solution().solution(numbers);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int[] numbers) {
            int sum = 0;
            boolean[] exists = new boolean[10];
            for (int n : numbers) {
                exists[n] = true;
            }
            for (int i = 0; i < 10; i++) {
                if (!exists[i]) {
                    sum += i;
                }
            }
            return sum;
        }

    }
}
