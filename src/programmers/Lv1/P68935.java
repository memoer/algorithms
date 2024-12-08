package programmers.Lv1;

public class P68935 {

    static class Solution {

        public int solution(int n) {
            String s = Integer.toString(n, 3);
            StringBuilder sb = new StringBuilder();
            int end = s.length() - 1;
            for (int i = end; i >= 0; i--) {
                sb.append(s.charAt(i));
            }
            return Integer.parseInt(sb.toString(), 3);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(45));
    }
}
