package programmers.highscore.dp;

import java.util.Arrays;

// 아 너무 어려운데? 이해가 안되는데?
//https://school.programmers.co.kr/questions/35224
public class P1843Retry {

    public static void main(String[] args) {
        String[] arr = {
            "5", "-", "3", "+", "1", "+", "2", "-", "4"
        };
        int solution = new Solution().solution(arr);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(String[] arr) {
            int n = arr.length / 2 + 1;
            int[][] max = new int[n][n];
            int[][] min = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(max[i], Integer.MIN_VALUE);
            }
            for (int i = 0; i < n; i++) {
                Arrays.fill(min[i], Integer.MAX_VALUE);
            }

            for (int step = 0; step < n; step++) {
                for (int i = 0; i < n - step; i++) {
                    int j = i + step;
                    if (step == 0) {
                        max[i][i] = min[i][i] = Integer.parseInt(arr[i * 2]);
                    } else {
                        for (int k = i; k < j; k++) {
                            String s = arr[k * 2 + 1];
                            if (s.equals("+")) {
                                max[i][j] = Math.max(max[i][j], max[i][k] + max[k + 1][j]);
                                min[i][j] = Math.min(min[i][j], min[i][k] + min[k + 1][j]);
                            } else {
                                max[i][j] = Math.max(max[i][j], max[i][k] - min[k + 1][j]);
                                min[i][j] = Math.min(min[i][j], min[i][k] - max[k + 1][j]);
                            }
                        }
                    }
                }
            }

            return max[0][n - 1];
        }
    }
}
