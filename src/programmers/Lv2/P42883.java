package programmers.Lv2;

import java.util.ArrayDeque;
import java.util.Deque;

public class P42883 {

    public static void main(String[] args) {
        String number = "1231234";
        int k = 3;
        String solution = new Solution().solution(number, k);
        System.out.println(solution);
    }

    private static class Solution {

        public String solution(String number, int k) {
            Deque<Integer> stack = new ArrayDeque<>();
            char[] chars = number.toCharArray();
            int len = chars.length;
            int count = k;
            int i = 0;

            for (; i < len && count != 0; i++) {
                int n = chars[i] - '0';
                if (stack.isEmpty()) {
                    stack.push(n);
                } else {
                    while (!stack.isEmpty() && stack.peek() < n && count != 0) {
                        stack.pop();
                        count -= 1;
                    }
                    stack.push(n);
                }
            }

            for (; i < len; i++) {
                stack.push(chars[i] - '0');
            }

            return getResult(stack, len - k);
        }

        private static String getResult(Deque<Integer> stack, int limit) {
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty() && sb.length() < limit) {
                sb.append(stack.pollLast());
            }
            return sb.toString();
        }
    }
}
