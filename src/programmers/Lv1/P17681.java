package programmers.Lv1;

import java.util.ArrayDeque;
import java.util.Deque;

public class P17681 {

    static class Solution {
        public String[] solution(int n, int[] arr1, int[] arr2) {
            String[] a = convert(n, arr1);
            String[] b = convert(n, arr2);
            String[] answer = new String[n];
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                String s1 = a[i];
                String s2 = b[i];
                for (int j = 0; j < n; j++) {
                    char c1 = s1.charAt(j);
                    char c2 = s2.charAt(j);
                    if (c1 == '#' || c2 == '#') {
                        sb.append("#");
                    } else {
                        sb.append(" ");
                    }
                }
                answer[i] = sb.toString();
            }
            return answer;
        }

        private String[] convert(int n, int[] arr) {
            String[] result = new String[n];
            Deque<Integer> dq = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                int v = arr[i];
                while (v >= 1) {
                    int mod = v % 2;
                    v /= 2;
                    dq.push(mod);
                }
                sb.append(" ".repeat(Math.max(0, n - dq.size())));
                while (!dq.isEmpty()) {
                    sb.append(dq.poll() == 1 ? "#" : " ");
                }
                result[i] = sb.toString();
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};
        // new Solution().solution(n, arr1, arr2);
        for (String s : new Solution().solution(n, arr1, arr2)) {
            System.out.println(s);
        }
    }
}
