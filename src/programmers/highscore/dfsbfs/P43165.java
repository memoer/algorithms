package programmers.highscore.dfsbfs;

public class P43165 {

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        int solution = new Solution().solution(numbers, target);
        System.out.println(solution);
    }

    private static class Solution {

        private int limit;
        private int target;
        private int answer = 0;
        private int[] numbers;

        public int solution(int[] numbers, int target) {
            this.limit = numbers.length;
            this.target = target;
            this.numbers = numbers;

            dfs(1, numbers[0]);
            dfs(1, -numbers[0]);

            return answer;
        }

        private void dfs(int idx, int acc) {
            if (idx >= limit) {
                if (acc == target) {
                    answer += 1;
                }
                return;
            }

            dfs(idx + 1, acc + numbers[idx]);
            dfs(idx + 1, acc - numbers[idx]);
        }

    }
}
