package programmers.highscore.stackqueue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class P42587 {

  public static void main(String[] args) {
    int[] priorities = new int[]{1, 1, 9, 1, 1, 1};
    int location = 0;
    new Solution().solution(priorities, location);
  }

  private static class Solution {

    public int solution(int[] priorities, int location) {
      Queue<Integer> q = new LinkedList<>();
      int answer = 0;

      for (int priority : priorities) {
        q.offer(priority);
      }
      while (!q.isEmpty()) {
        Integer max = Collections.max(q);
        Integer v = q.poll();
        location -= 1;

        if (v < max) {
          q.offer(v);
          if (location < 0) {
            location = q.size() - 1;
          }
        } else {
          answer += 1;
          if (location == -1) {
            break;
          }
        }
      }
      return answer;
    }
  }
}
