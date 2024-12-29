package programmers.Lv1;

public class P250125 {

    public static void main(String[] args) {

    }

    private static class Solution {

        public int solution(String[][] board, int h, int w) {
            int len = board.length;
            int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            String color = board[h][w];

            int answer = 0;
            for (int[] arr : direction) {
                int dh = h + arr[0];
                int dw = w + arr[1];
                if (dh < 0 || dw < 0 || dh >= len || dw >= len) {
                    continue;
                }

                if (board[dh][dw].equals(color)) {
                    answer += 1;
                }
            }
            return answer;
        }
    }
}
