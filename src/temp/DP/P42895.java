package temp.DP;

import java.util.HashSet;
import java.util.Set;

public class P42895 {
  public static void main(String[] args) {
    int N = 5;
    int number = 12;
    System.out.println(new Solution().solution(N, number));
  }

  static class Solution {
    private Set<Integer>[] s;
    public static final int MAX_RANGE = 9;
    public static final int MIN_RANGE = 1;

    private void initialize() {
      s = new HashSet[MAX_RANGE];
      for (int i = 0; i < MAX_RANGE; i++) {
        s[i] = new HashSet<>();
      }
    }

    public int solution(int N, int number) {
      int result = -1;
      int tempN = N;
      initialize();
      for (int i = MIN_RANGE; i < MAX_RANGE; i++) {
        s[i].add(tempN);
        tempN = tempN * 10 + N;
        for (int j = MIN_RANGE; j < i; j++) {
          for (int num1 : s[i - j]) {
            for (int num2 : s[j]) {
              s[i].add(num1 + num2);
              s[i].add(num1 - num2);
              s[i].add(num2 - num1);
              s[i].add(num1 * num2);
              if (num1 != 0) {
                s[i].add(num2 / num1);
              }
              if (num2 != 0) {
                s[i].add(num1 / num2);
              }
            }
          }
        }
        if (s[i].contains(number)) {
          result = i;
          break;
        }
      }
      return result;
    }
  }
}
