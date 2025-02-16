package programmers.Lv2;

import java.util.ArrayDeque;
import java.util.Deque;

public class P76502_2 {

    public static void main(String[] args) {
        String s = "}]()[{";
        int solution = new Solution().solution(s);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(String s) {
            int answer = 0;
            char[] arr = s.toCharArray();
            int len = arr.length;
            for (int i = 0; i < len; i++) {
                char[] moved = move(i, arr, len);
                answer += (isValid(moved) ? 1 : 0);
            }
            return answer;
        }

        private char[] move(int n, char[] arr, int size) {
            int idx = 0;
            char[] moved = new char[size];

            for (int i = n; i < size; i++) {
                moved[idx++] = arr[i];
            }
            for (int i = 0; i < n; i++) {
                moved[idx++] = arr[i];
            }

            return moved;
        }

        private boolean isValid(char[] arr) {
            Deque<Character> dq = new ArrayDeque<>();
            for (char c : arr) {
                if (dq.isEmpty()) {
                    dq.push(c);
                } else if (isPair(dq.peek(), c)) {
                    dq.pop();
                } else {
                    dq.push(c);
                }
            }
            return dq.isEmpty();
        }

        private boolean isPair(char peek, char c) {
            return switch (peek) {
                case '[' -> c == ']';
                case '{' -> c == '}';
                case '(' -> c == ')';
                default -> false;
            };
        }
    }
}
