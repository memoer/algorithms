package programmers.Lv3;

import java.util.HashSet;
import java.util.Set;

public class P42895 {
  public static void main(String[] args) {
    System.out.println(new Solution().solution(8, 53));
  }

  private static class Solution {
    final int MAX = 9;
    int N;
    Set<Integer>[] set;

    public int solution(int N, int number) {
      if (N == number) return 1;
      this.N = N;
      this.set = new HashSet[MAX];
      for (int i = 1; i < MAX; i++) {
        combination(i);
        if (set[i].contains(number)) return i;
      }

      return -1;
    }

    private void combination(int i) {
      set[i] = new HashSet<>();
      int temp = N;
      for (int j = 1; j < i; j++) temp = temp * 10 + N;
      set[i].add(temp);

      for (int j = 1; j < i; j++) {
        // i=3 / j=1,2 / k=2,1
        int k = i - j;
        for (int num1 : set[j])
          for (int num2 : set[k]) {
            set[i].add(num1 + num2);
            set[i].add(num1 - num2);
            set[i].add(num1 * num2);
            if (num2 != 0) set[i].add(num1 / num2);
          }
      }
    }
  }
}
