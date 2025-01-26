package programmers.Lv2;

public class P17687_2 {

    public static void main(String[] args) {
        String solution = new Solution().solution(16, 16, 2, 1);
        System.out.println(solution);
    }

    private static class Solution {

        public String solution(int n, int t, int m, int p) {
            StringBuilder sb = new StringBuilder();
            int num = 0;
            int idx = 0;
            String radix = Integer.toString(num, n);
            int turn = 1;

            while (sb.length() < t) {
                if (turn == p) {
                    sb.append(radix.charAt(idx));
                }

                if (turn + 1 > m) {
                    turn = 1;
                } else {
                    turn += 1;
                }

                if (idx + 1 < radix.length()) {
                    idx += 1;
                } else {
                    idx = 0;
                    num += 1;
                    radix = Integer.toString(num, n);
                }
            }

            return sb.toString().toUpperCase();
        }

    }
}
