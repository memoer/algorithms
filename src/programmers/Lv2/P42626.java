package programmers.Lv2;

import java.util.PriorityQueue;

public class P42626 {
  static class Solution {
    public int solution(int[] s, int K) {
      int answer = 0;
      PriorityQueue<Integer> pq = new PriorityQueue<>();
      for (int n : s) {
        pq.offer(n);
      }

      while (!pq.isEmpty() && pq.peek() < K) {
        int a = pq.poll();
        if (pq.isEmpty()) {
          if (a < K) {
            answer = -1;
          }
          break;
        } else {
          int b = pq.poll();
          pq.offer(a + (b * 2));
          answer += 1;
        }
      }

      return answer;
    }
  }

  public static void main(String[] args) {
    int[] s = { 1, 2, 3, 9, 10, 12 };
    int K = 7;
    // new Solution().solution(s, K);
    System.out.println(new Solution().solution(s, K));
  }

}
