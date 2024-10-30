package programmers.highscore.heap;

import java.util.PriorityQueue;

public class P42627 {

    public static void main(String[] args) {

    }

    private static class Solution {

        public int solution(int[][] jobs) {
            PriorityQueue<int[]> request = new PriorityQueue<>(
                (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
            for (int[] job : jobs) {
                request.offer(job);
            }
            PriorityQueue<int[]> task = new PriorityQueue<>(
                (o1, o2) -> o1[1] != o2[1] ? o1[1] - o2[1] : o1[0] - o2[0]);

            int time = 0;
            int sum = 0;
            while (!request.isEmpty() || !task.isEmpty()) {
                if (task.isEmpty()) {
                    int[] poll = request.poll();
                    sum += poll[1];
                    time = Math.max(time + poll[1], poll[0] + poll[1]);
                } else {
                    int[] poll = task.poll();
                    sum += (time - poll[0]) + poll[1];
                    time += poll[1];
                }

                while (!request.isEmpty()) {
                    if (request.peek()[0] > time) {
                        break;
                    }
                    int[] poll = request.poll();
                    task.offer(poll);
                }
            }

            return sum / jobs.length;
        }

    }
}

