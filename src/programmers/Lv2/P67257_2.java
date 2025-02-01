package programmers.Lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P67257_2 {

    public static void main(String[] args) {
        long solution = new Solution().solution("100-200*300-500+20");
        System.out.println(solution);
    }

    private static class Solution {

        private final List<String[]> p = new ArrayList<>();

        public long solution(String exp) {
            permutation(0, init(exp));

            long answer = Long.MIN_VALUE;
            for (String[] ops : p) {
                long sum = sum(ops, exp, 0);
                answer = Math.max(answer, Math.abs(sum));
            }

            return answer;
        }

        private String[] init(String exp) {
            Set<String> set = new HashSet<>();
            char[] arr = exp.toCharArray();
            for (char c : arr) {
                if (set.size() == 3) {
                    break;
                }
                if (c == '+') {
                    set.add("\\+");
                }
                if (c == '-') {
                    set.add("\\-");
                }
                if (c == '*') {
                    set.add("\\*");
                }
            }
            return set.toArray(String[]::new);
        }

        private void permutation(int depth, String[] arr) {
            if (depth >= arr.length) {
                p.add(Arrays.copyOfRange(arr, 0, arr.length));
                return;
            }

            for (int i = depth; i < arr.length; i++) {
                swap(arr, i, depth);
                permutation(depth + 1, arr);
                swap(arr, i, depth);
            }
        }

        private void swap(String[] arr, int i, int j) {
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        private long sum(String[] ops, String exp, int depth) {
            if (depth >= ops.length) {
                return Long.parseLong(exp);
            }

            String op = ops[depth];
            String[] split = exp.split(op);
            int len = split.length;
            long temp = sum(ops, split[0], depth + 1);
            for (int i = 1; i < len; i++) {
                long v = sum(ops, split[i], depth + 1);
                if (op.equals("\\+")) {
                    temp += v;
                } else if (op.equals("\\-")) {
                    temp -= v;
                } else {
                    temp *= v;
                }
            }
            return temp;
        }

    }
}
