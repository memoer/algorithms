package programmers.review;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class R42587 {
  static class Solution {
    public int solution(int[] priorities, int location) {
      int answer = 0;
      int l = location;
      Queue<Integer> q = new LinkedList<>();
      for (int i = 0; i < priorities.length; i++) {
        q.offer(priorities[i]);
      }
      Arrays.sort(priorities);
      int size = priorities.length - 1;
      while (!q.isEmpty()) {
        int p = q.poll();
        int highP = priorities[size - answer];
        l--;
        if (highP > p) {
          q.offer(p);
          if (l < 0) {
            l = q.size() - 1;
          }
        } else {
          answer++;
          if (l < 0) {
            break;
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
