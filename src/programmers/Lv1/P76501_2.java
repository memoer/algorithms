package programmers.Lv1;

public class P76501_2 {

    public static void main(String[] args) {
        int[] a = {4, 7, 12};
        boolean[] b = {true, false, true};
        int solution = new Solution().solution(a, b);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int[] absolutes, boolean[] signs) {
            int size = absolutes.length;
            int sum = 0;
            for (int i = 0; i < size; i++) {
                int n = absolutes[i];
                boolean b = signs[i];
                if (b) {
                    sum += n;
                } else {
                    sum -= n;
                }
            }
            return sum;
        }

    }
}
