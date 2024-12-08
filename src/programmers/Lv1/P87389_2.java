package programmers.Lv1;

public class P87389_2 {

    public static void main(String[] args) {
        int solution = new Solution().solution(10);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int n) {
            int answer = 1;
            while (n % ++answer!= 1) {
            }
            return answer;
        }

    }
}
