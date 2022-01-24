package programmers.stackNQueue;

import java.util.Arrays;

public class S42586Other {
  static class Solution {
    public int[] solution(int[] p, int[] s) {
      int[] dayOfEnd = new int[101];
      int day = 0;
      for (int i = 0; i < p.length; i++) {
        while (p[i] + s[i] * day < 100) {
          day++;
        }
        dayOfEnd[day]++;
      }
      return Arrays.stream(dayOfEnd).filter(x -> x != 0).toArray();
    }
  }

  public static void main(String[] args) {
    int[] p = { 93, 30, 55 };
    int[] s = { 1, 30, 5 };
    System.out.println(new Solution().solution(p, s));
  }
}
