package programmers.stackNQueue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Q42587 {

  static class Solution {

    public int solution(int[] priorities, int location) {
      int answer = 0;
      int l = location;
      int pIdx = priorities.length - 1;
      Queue<Integer> q = new LinkedList<>();
      for (int p : priorities) {
        q.add(p);
      }
      Arrays.sort(priorities);
      while (true) {
        int p = q.poll();
        if (p == priorities[pIdx - answer]) {
          answer++;
          l--;
          if (l < 0) {
            break;
          }
        } else {
          q.add(p);
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
    int[] priorities = { 1, 1, 9, 1, 1, 1 };
    int location = 0;
    System.out.println(new Solution().solution(priorities, location));
  }
}
