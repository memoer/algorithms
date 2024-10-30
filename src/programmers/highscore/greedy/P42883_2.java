package programmers.highscore.greedy;

import java.util.ArrayDeque;
import java.util.Deque;

public class P42883_2 {

    public static void main(String[] args) {
        String number = "9876543214";
        int k = 4;
        String solution = new Solution().solution(number, k);
        System.out.println(solution);
    }

    private static class Solution {

        public String solution(String number, int k) {
            Deque<Character> dq = new ArrayDeque<>();
            int len = number.length();
            int count = k;

            for (int i = 0; i < len; i++) {
                char c = number.charAt(i);
                while (!dq.isEmpty() && count != 0 && dq.peek() < c) {
                    dq.pop();
                    count -= 1;
                }
                dq.push(c);
            }

            StringBuilder sb = new StringBuilder();
            while (!dq.isEmpty()) {
                sb.append(dq.pollLast());
            }
            return sb.toString();
        }

    }
}
