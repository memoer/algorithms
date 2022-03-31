package programmers.Lv2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class P42587 {
  static class Solution {
    public int solution(int[] priorities, int location) {
      Queue<Integer> q = new LinkedList<>();
      int answer = 0;
      for (int p : priorities) {
        q.offer(p);
      }
      while (!q.isEmpty()) {
        int max = Collections.max(q);
        int cur = q.poll();
        if (cur == max) {
          answer += 1;
          if (location == 0) {
            break;
          } else {
            location -= 1;
          }
        } else {
          q.offer(cur);
          if (location == 0) {
            location = q.size() - 1;
          } else {
            location -= 1;
          }
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] priorities = { 1, 1, 9, 1, 1, 1 };
    int location = 0;
    System.out.println(new Solution().solution(priorities, location));
  }
}
