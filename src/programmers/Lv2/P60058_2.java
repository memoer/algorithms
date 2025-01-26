package programmers.Lv2;

import java.util.ArrayDeque;
import java.util.Deque;

public class P60058_2 {

    public static void main(String[] args) {
        String p = "()))((()";
        String solution = new Solution().solution(p);
        System.out.println(solution);
    }

    private static class Solution {

        public String solution(String p) {
            StringBuilder sb = process(new StringBuilder(p));
            return sb.toString();
        }

        private StringBuilder process(StringBuilder sb) {
            int len = sb.length();
            if (len == 0) {
                return sb;
            }

            StringBuilder u = new StringBuilder();
            StringBuilder v = new StringBuilder();
            Deque<Character> dq = new ArrayDeque<>();
            int l = 0, r = 0;
            int idx = 0;
            for (; idx < len ; idx++) {
                char ch = sb.charAt(idx);
                u.append(ch);
                if (ch == '(') {
                    dq.push(ch);
                    l += 1;
                } else {
                    if (!dq.isEmpty() && dq.peek() == '(') {
                        dq.pop();
                    } else {
                        dq.push(ch);
                    }
                    r += 1;
                }

                if (l == r) {
                    idx += 1;
                    break;
                }
            }

            for (; idx < len; idx++) {
                v.append(sb.charAt(idx));
            }

            if (dq.isEmpty()) {
                return u.append(process(v));
            } else {
                StringBuilder reversed = reverse(u);
                return process(v).insert(0, "(").append(")").append(reversed);
            }
        }

        private StringBuilder reverse(StringBuilder sb) {
            sb.deleteCharAt(0).deleteCharAt(sb.length() - 1);
            int len = sb.length();
            for (int i = 0; i < len; i++) {
                if (sb.charAt(i) == ')') sb.replace(i, i + 1, "(");
                else sb.replace(i, i + 1, ")");
            }
            return sb;
        }
    }
}
