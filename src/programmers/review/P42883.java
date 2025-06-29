package programmers.review;

import java.util.ArrayDeque;
import java.util.Deque;

public class P42883 {

    public static void main(String[] args) {
        String s = new Solution().solution("4177252841", 4);
        System.out.println(s);
    }

    private static class Solution {

        public String solution(String number, int k) {
            Deque<Character> dq = new ArrayDeque<>();
            int len = number.length();
            int count = k;

            for (int i = 0; i < len; i++) {
                char c = number.charAt(i);
                while (!dq.isEmpty() && count != 0 && c > dq.peek()) {
                    dq.pop();
                    count -= 1;
                }
                dq.push(c);
            }
//            5 - 2
//            dq -> 4
            int limit = len - k;
            StringBuilder sb = new StringBuilder();
            while (sb.length() < limit && !dq.isEmpty()) {
                sb.append(dq.pollLast());
            }
            return sb.toString();
        }
    }
}
