package temp.Stack;

import java.util.Arrays;

public class Stack42586 {
  static class Solution {
    public int[] solution(int[] p, int[] s) {
      int[] dayOfEnd = new int[100];
      int day = 1;
      for (int i = 0; i < p.length; i++) {
        while (p[i] + s[i] * day < 100) {
          ++day;
        }
        ++dayOfEnd[day];
      }
      return Arrays.stream(dayOfEnd).filter(x -> x != 0).toArray();
    }
  }

  public static void main(String[] args) {
    int[] p = { 93, 30, 55 };
    int[] s = { 1, 30, 5 };
    for (int n : new Solution().solution(p, s)) {
      System.out.print(n + ",");
    }
    System.out.println();
  }
}
