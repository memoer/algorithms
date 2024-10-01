package programmers.highscore.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class P42884 {

    public static void main(String[] args) {

    }

    private static class Solution {

        public int solution(int[][] routes) {
            int len = routes.length;
            int answer = len;
            Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));

            int target = routes[0][1];
            for (int i = 1; i < len; i++) {
                int start = routes[i][0];
                int end = routes[i][1];

                if (start <= target) {
                    answer -= 1;
                } else {
                    target = end;
                }
            }

            return answer;
        }

    }
}
