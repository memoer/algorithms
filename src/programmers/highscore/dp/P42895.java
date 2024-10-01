package programmers.highscore.dp;

import java.util.HashSet;
import java.util.Set;

public class P42895 {

    public static void main(String[] args) {
        int N = 2;
        int number = 11;
        int solution = new Solution().solution(N, number);
        System.out.println(solution);

    }

    private static class Solution {

        public int solution(int N, int number) {
            if (N == number) {
                return 1;
            }

            int limit = 9;
            int tempN = 0;
            Set<Integer>[] set = new Set[limit];
            for (int i = 1; i < limit; i++) {
                set[i] = new HashSet<>();
                tempN = tempN * 10 + N;
                set[i].add(tempN);
            }

            for (int count = 2; count < limit; count++) {
                int mid = count / 2;
                for (int left = 1; left <= mid; left++) {
                    int right = count - left;
                    for (Integer num1 : set[left]) {
                        for (Integer num2 : set[right]) {
                            set[count].add(num1 + num2);
                            set[count].add(num1 - num2);
                            set[count].add(num2 - num1);
                            set[count].add(num1 * num2);
                            if (num1 != 0) {
                                set[count].add(num2 / num1);
                            }
                            if (num2 != 0) {
                                set[count].add(num1 / num2);
                            }
                        }
                    }
                }

                if (set[count].contains(number)) {
                    return count;
                }
            }

            return -1;
        }

    }
}
