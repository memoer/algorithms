package programmers.Lv2;

public class P118667 {

    public static void main(String[] args) {
        int[] q1 = {3, 2, 7, 2};
        int[] q2 = {4, 6, 5, 1};
        int solution = new Solution().solution(q1, q2);
        System.out.println(solution);
    }

    private static class Solution {

        private final int LEN = 300_000;

        public int solution(int[] queue1, int[] queue2) {
            int[] q1 = new int[LEN * 2];
            int[] q2 = new int[LEN * 2];
            int count1 = queue1.length;
            int count2 = queue2.length;
            long sum1 = 0, sum2 = 0;
            int s1 = 0, s2 = 0, e1 = count1, e2 = count2;
            int answer = 0;

            for (int i = 0; i < count1; i++) {
                q1[i] = queue1[i];
                sum1 += queue1[i];
            }
            for (int i = 0; i < count2; i++) {
                q2[i] = queue2[i];
                sum2 += queue2[i];
            }

            while (true) {
                if (count1 == 0 || count2 == 0 || answer >= LEN * 3) {
                    return -1;
                }

                if (sum1 > sum2) {
                    int v = q1[s1++];
                    q2[e2++] = v;

                    count1 -= 1;
                    count2 += 1;
                    sum1 -= v;
                    sum2 += v;
                } else if (sum1 < sum2) {
                    int v = q2[s2++];
                    q1[e1++] = v;

                    count2 -= 1;
                    count1 += 1;
                    sum2 -= v;
                    sum1 += v;
                } else {
                    break;
                }
                answer += 1;
            }
            return answer;
        }
    }
}
