package programmers.Lv1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P68644 {

    static class Solution {

        public int[] solution(int[] numbers) {
            int len = numbers.length;
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < len - 1; i++) {
                for (int j = i + 1; j < len; j++) {
                    set.add(numbers[i] + numbers[j]);
                }
            }
            return set.stream().sorted().mapToInt(v -> v).toArray();
        }
    }

    public static void main(String[] args) {
        int[] numbers = {2, 1, 3, 4, 1};
        for (int num : new Solution().solution(numbers)) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }
}
