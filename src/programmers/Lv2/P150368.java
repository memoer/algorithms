package programmers.Lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P150368 {

    public static void main(String[] args) {
        int[][] users = {
            {40, 10000}, {25, 10000}
        };
        int[] emoticons = {7_000, 9_000};

        int[] solution = new Solution().solution(users, emoticons);
        for (int v : solution) {
            System.out.print(v + ", ");
        }
        System.out.println();
    }

    private static class Solution {

        int[] rate = {10, 20, 30, 40};
        List<int[]> rates = new ArrayList<>();

        public int[] solution(int[][] users, int[] emoticons) {
            int[] answer = new int[2];
            Arrays.fill(answer, 0);
            int len = emoticons.length;

            permutation(len, 0, new int[len]);

            for (int[] rate : rates) {
                int subscribers = 0;
                int totalSales = 0;

                for (int[] user : users) {
                    int sum = 0;
                    for (int i = 0; i < len; i++) {
                        int r = rate[i];
                        if (user[0] > r) {
                            continue;
                        }

                        int price = (int) (emoticons[i] - emoticons[i] * r / 100.0);
                        sum += price;
                    }

                    if (sum >= user[1]) {
                        subscribers += 1;
                    } else {
                        totalSales += sum;
                    }
                }

                if (answer[0] < subscribers) {
                    answer[0] = subscribers;
                    answer[1] = totalSales;
                } else if (answer[0] == subscribers) {
                    if (answer[1] < totalSales) {
                        answer[1] = totalSales;
                    }
                }
            }
            return answer;
        }

        public void permutation(int n, int depth, int[] temp) {
            if (depth >= n) {
                this.rates.add(Arrays.copyOf(temp, n));
                return;
            }

            for (int i = 0; i < 4; i++) {
                temp[depth] = this.rate[i];
                permutation(n, depth + 1, temp);
                temp[depth] = 0;
            }
        }
    }
}
