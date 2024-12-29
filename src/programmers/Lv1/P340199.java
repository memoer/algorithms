package programmers.Lv1;

public class P340199 {

    public static void main(String[] args) {

    }

    private static class Solution {

        public int solution(int[] wallet, int[] bill) {
            int answer = 0;

            while (true) {
                int a = bill[0];
                int b = bill[1];
                boolean isValid = (wallet[0] >= a && wallet[1] >= b) || (wallet[0] >= b && wallet[1] >= a);
                if (isValid) {
                    break;
                }

                bill[bill[0] > bill[1] ? 0 : 1] /= 2;
                answer += 1;
            }

            return answer;
        }

    }
}
