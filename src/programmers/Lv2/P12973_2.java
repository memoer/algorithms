package programmers.Lv2;

import java.util.ArrayDeque;
import java.util.Deque;

public class P12973_2 {

    public static void main(String[] args) {

    }

    private static class Solution {

        public int solution(String s) {
            Deque<Character> dq = new ArrayDeque<>();

            char[] arr = s.toCharArray();
            for (char c : arr) {
                if(!dq.isEmpty() && dq.peek() == c) {
                    dq.pop();
                }
                else{
                    dq.push(c);
                }
            }

            return dq.isEmpty() ? 1 : 0;
        }

    }
}
