package temp.Queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Queue42587 {
  static class Solution {

    public int solution(int[] priorities, int location) {
      int answer = 0;
      int l = location;
      int pIdx = priorities.length - 1;
      Queue<Integer> q = new LinkedList<>();
      for (int priority : priorities) {
        q.add(priority);
      }
      Arrays.sort(priorities);
      while (true) {
        int priority = q.poll();
        if (priority == priorities[pIdx - answer]) {
          answer++;
          l--;
          if (l < 0) {
            break;
          }
        } else {
          q.add(priority);
          l--;
          if (l < 0) {
            l = q.size() - 1;
          }
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] priorities = { 2, 1, 3, 2 };
    int location = 2;
    System.out.println(new Solution().solution(priorities, location));
  }
}
