package programmers.problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

public class P12946 {

    public static void main(String[] args) {
        int[][] solution = new Solution().solution(2);
        for (int[] ints : solution) {
            for (int anInt : ints) {
                System.out.print(anInt+", ");
            }
            System.out.println();
        }
    }

    private static class Solution {

        public int[][] solution(int n) {
            List<int[]> answer = new ArrayList<>();
            Deque<Integer> dq1 = getDq(n);
            Deque<Integer> dq2 = new ArrayDeque<>();
            Deque<Integer> dq3 = new ArrayDeque<>();

            return answer.toArray(new int[answer.size()][]);
        }

        private Deque<Integer> getDq(int n) {
            Deque<Integer> dq = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) {
                dq.push(i);
            }
            return dq;
        }
    }
}
