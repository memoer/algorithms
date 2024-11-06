package programmers.Lv1;

import java.util.ArrayDeque;
import java.util.Deque;

public class P64061 {

    static class Solution {

        public int solution(int[][] board, int[] moves) {
            int answer = 0;
            Deque<Integer> dq = new ArrayDeque<>();

            for (int move : moves) {
                int i = move - 1;
                for (int[] row : board) {
                    if (row[i] == 0) {
                        continue;
                    }
                    int target = row[i];
                    row[i] = 0;
                    if (dq.isEmpty()) {
                        dq.push(target);
                    } else if (dq.peek() == target) {
                        dq.poll();
                        answer += 2;
                    } else {
                        dq.push(target);
                    }
                    break;
                }
            }

            return answer;
        }
    }

    public static void main(String[] args) {
        int[][] board = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 3},
            {0, 2, 5, 0, 1},
            {4, 2, 4, 4, 2},
            {3, 5, 1, 3, 1},
        };
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        System.out.println(new Solution().solution(board, moves));
    }
}
