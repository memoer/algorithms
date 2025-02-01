package programmers.Lv2;

public class P70129_2 {

    public static void main(String[] args) {
        String s = "110010101001";
        int[] solution = new Solution().solution(s);
        for (int i : solution) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static class Solution {

        public int[] solution(String s) {
            int[] answer = new int[2];
            String temp = s;
            int count = 0;
            while (temp.length() > 1) {
                answer[0] += 1;
                char[] arr = temp.toCharArray();
                for (char c : arr) {
                    if (c == '0') {
                        count += 1;
                    }
                }
                temp = Integer.toBinaryString(temp.length() - count);
                answer[1] += count;
                count = 0;
            }
            return answer;
        }

    }
}
