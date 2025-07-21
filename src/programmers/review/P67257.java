package programmers.review;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P67257 {

    public static void main(String[] args) {
        long solution = new Solution().solution("100-200*300-500+20");
        System.out.println(solution);
    }

    private static class Solution {

        private Set<String> set = new HashSet<>();
        private long answer = Long.MIN_VALUE;

        public long solution(String exp) {
            List<String> ops = find(exp);
            permutation(ops, new boolean[ops.size()], new StringBuilder());
            for (String s : set) {
                char[] arr = s.toCharArray();
                long sum = sum(exp, arr, 0);
                answer = Math.max(answer, Math.abs(sum));
            }
            return answer;
        }

        private List<String> find(String exp) {
            char[] arr = exp.toCharArray();
            Set<String> ops = new HashSet<>();
            for (char c : arr) {
                if (!Character.isDigit(c)) {
                    ops.add(String.valueOf(c));
                }
            }

            List<String> list = new ArrayList<>();
            for (String op : ops) {
                list.add(op);
            }
            return list;
        }

        private void permutation(List<String> ops, boolean[] visited, StringBuilder sb) {
            if (sb.length() == visited.length) {
                set.add(sb.toString());
                return;
            }
            for (int i = 0; i < ops.size(); i++) {
                if (visited[i]) {
                    continue;
                }
                visited[i] = true;
                sb.append(ops.get(i));
                permutation(ops, visited, sb);
                sb.deleteCharAt(sb.length() - 1);
                visited[i] = false;
            }
        }

        private long sum(String exp, char[] ops, int idx) {
            if (ops.length == idx) {
                return Long.parseLong(exp);
            }

            char op = ops[idx];
            String[] split = exp.split(change(op));
            int len = split.length;
            long temp = sum(split[0], ops, idx + 1);
            for (int i = 1; i < len; i++) {
                long n = sum(split[i], ops, idx + 1);
                if (op == '+') {
                    temp += n;
                } else if (op == '-') {
                    temp -= n;
                } else {
                    temp *= n;
                }
            }
            return temp;
        }

        private String change(char op) {
            return switch (op) {
                case '+' -> "\\+";
                case '-' -> "\\-";
                case '*' -> "\\*";
                default -> throw new UnsupportedOperationException();
            };
        }

    }
}
