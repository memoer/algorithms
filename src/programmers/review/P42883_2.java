package programmers.review;

import java.util.ArrayDeque;
import java.util.Deque;

public class P42883_2 {

    public static void main(String[] args) {
        String solution = new Solution().solution("4177252841", 4);
        System.out.println(solution);
    }

    private static class Solution {

        public String solution(String number, int k) {
            Deque<Integer> dq = get(number, k);
            return getString(dq);
        }

        private Deque<Integer> get(String number, int k) {
            Deque<Integer> dq = new ArrayDeque<>();
            int count = k;
            char[] arr = number.toCharArray();
            int len = number.length();

            for (int i = 0; i < len; i++) {
                int n = arr[i] - '0';
                while (!dq.isEmpty() && count > 0 && dq.peek() < n) {
                    dq.pop();
                    count -= 1;
                }
                dq.push(n);
            }

            for (; count > 0 && !dq.isEmpty(); count--) {
                dq.pop();
            }

            return dq;
        }

        private String getString(Deque<Integer> dq) {
            StringBuilder sb = new StringBuilder();
            while (!dq.isEmpty()) {
                sb.append(dq.pollLast());
            }
            return sb.toString();
        }
    }
}
