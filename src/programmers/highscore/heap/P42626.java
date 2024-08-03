package programmers.highscore.heap;

import java.util.PriorityQueue;

public class P42626 {

  public static void main(String[] args) {
    int[] s = new int[]{1, 2, 3, 9, 10, 12};
    int K = 7;
    int solution = new Solution().solution(s, K);
    System.out.println(solution);
  }

  private static class Solution {

    public int solution(int[] s, int K) {
      int answer = 0;
      PriorityQueue<Integer> pq = new PriorityQueue<>();
      for (int i : s) {
        pq.offer(i);
      }

      while (!pq.isEmpty()) {
        Integer a = pq.poll();
        if (a >= K) {
          break;
        } else if (pq.peek() == null) {
          answer = -1;
          break;
        }
        Integer b = pq.poll();
        pq.offer(a + (b * 2));
        answer += 1;
      }
      return answer;
    }
  }
}
