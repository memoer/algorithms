package programmers.Lv2;

public class P12985_2 {

    public static void main(String[] args) {
        int solution = new Solution().solution(2, 1, 2);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int n, int a, int b) {
            int answer = 0;
            int tempA = a;
            int tempB = b;
            int len = n / 2;

            for (int i = 0; i < len; i++) {
                answer += 1;
                if(sameGroup(tempA, tempB)) {
                    break;
                }

                tempA = next(tempA);
                tempB = next(tempB);
            }


            return answer;
        }

        private int next(int num) {
            int div = num / 2;
            return num % 2 == 0 ? div : div + 1;
        }

        private boolean sameGroup(int a, int b) {
            int modA = a % 2;
            int modB = b % 2;
            if (modA == 1) {
                return a < b && a + 1 == b;
            } else if (modB == 1) {
                return b < a && b + 1 == a;
            } else {
                return false;
            }
        }

    }
}
