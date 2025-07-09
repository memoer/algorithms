package programmers.review;

import java.util.HashSet;
import java.util.Set;

public class P42895 {

    public static void main(String[] args) {
        int solution = new Solution().solution(2, 11);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int N, int number) {
            Set<Integer>[] set = new HashSet[10];

            int tempN = 0;
            for (int i = 1; i < 9; i++) {
                tempN = tempN * 10 + N;
                if (tempN == number) {
                    return i;
                }

                set[i] = new HashSet<>();
                set[i].add(tempN);
                for (int j = 1; j < i; j++) {
                    int k = i - j;
                    for (int num1 : set[j]) {
                        for (int num2 : set[k]) {
                            set[i].add(num1 + num2);
                            set[i].add(num1 - num2);
                            set[i].add(num1 * num2);
                            if (num2 != 0) {
                                set[i].add(num1 / num2);
                            }
                        }
                    }
                }
                for (int num : set[i]) {
                    if (num == number) {
                        return i;
                    }
                }
            }

            return -1;
        }


    }
}
