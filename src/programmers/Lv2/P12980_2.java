package programmers.Lv2;

public class P12980_2 {

    public static void main(String[] args) {
        System.out.println(Integer.bitCount(5));

//        int solution = new Solution().solution(5_000);
//        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int n) {
            int answer = 0;
            int temp = n;
            while (temp > 0) {
                if (temp % 2 != 0) {
                    temp -= 1;
                    answer += 1;
                }
                temp /= 2;
            }
            return answer;
        }

    }
}
