package programmers.Lv1;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class P77484_2 {

    public static void main(String[] args) {
        int[] a = {44, 1, 0, 0, 31, 25};
        int[] b = {31, 10, 45, 1, 6, 19};
        int[] solution = new Solution().solution(a, b);
        System.out.println(solution[0] + ", " + solution[1]);
    }

    private static class Solution {

        public int[] solution(int[] lottos, int[] win_nums) {
            int contains = 0;
            int zero = 0;
            Set<Integer> set = Arrays.stream(win_nums).boxed().collect(Collectors.toSet());
            for (int lotto : lottos) {
                if (lotto == 0) {
                    zero += 1;
                } else if (set.contains(lotto)) {
                    contains += 1;
                }
            }
            return new int[]{get(contains + zero), get(contains)};
        }

        private int get(int contains) {
            return switch (contains) {
                case 0, 1 -> 6;
                case 2 -> 5;
                case 3 -> 4;
                case 4 -> 3;
                case 5 -> 2;
                case 6 -> 1;
                default -> 0;
            };
        }

    }
}
