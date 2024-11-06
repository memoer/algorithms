package programmers.Lv1;

import java.util.Arrays;

public class P12982 {

    public static void main(String[] args) {
        int[] d = {2, 2, 3, 3};
        int budget = 10;
        System.out.println(new Solution().solution(d, budget));
    }

    static class Solution {

        public int solution(int[] d, int budget) {
            int answer = 0;
            int sum = 0;
            Arrays.sort(d);
            for (int v : d) {
                if (v + sum <= budget) {
                    answer += 1;
                    sum += v;
                }
            }
            return answer;
        }
    }
}
