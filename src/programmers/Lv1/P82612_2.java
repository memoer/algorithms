package programmers.Lv1;

public class P82612_2 {

    public static void main(String[] args) {
        long solution = new Solution().solution(3, 20, 4);
        System.out.println(solution);
    }

    private static class Solution {

        public long solution(int price, int money, int count) {
            long acc = 0;
            for (int i = 1; i <= count; i++) {
                acc += ((long) price * i);
            }
            return Math.max(acc - money, 0);
        }

    }
}
