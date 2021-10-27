package programmers.review;

import java.util.Arrays;

public class R42586 {
  static class Solution {
    public int[] solution(int[] p, int[] s) {
      int[] dayOfEnd = new int[100];
      int day = 0;
      for (int i = 0; i < p.length; i++) {
        while (p[i] + day * s[i] < 100) {
          day++;
        }
        dayOfEnd[day]++;
      }
      return Arrays.stream(dayOfEnd).filter(i -> i != 0).toArray();
    }
  }

  public static void main(String[] args) {
    int[] p = { 93, 30, 55 };
    int[] s = { 1, 30, 5 };
    for (int i : new Solution().solution(p, s)) {
      System.out.print(i + ", ");
    }
    System.out.println();
  }
}
