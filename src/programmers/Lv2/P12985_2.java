package programmers.Lv2;

public class P12985_2 {

    public static void main(String[] args) {
        int solution = new Solution().solution(8, 4, 7);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int n, int a, int b) {
            int answer = 1;
            int tempA = a;
            int tempB = b;
            int len = n / 2;

            for (int i = 0; i < len; i++) {
                if (tempA == tempB + 1 || tempA + 1 == tempB) {
                    break;
                }

                tempA = change(tempA);
                tempB = change(tempB);
                answer += 1;
            }

            return answer;
        }

        private int change(int tempA) {
            int mod = tempA % 2;
            int div = tempA / 2;
            tempA = mod == 0 ? div : div + 1;
            return tempA;
        }

    }
}
