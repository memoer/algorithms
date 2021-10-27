package programmers.DP;

import java.util.HashSet;
import java.util.Set;

public class D42895 {
  static class Solution {
    public int solution(int N, int number) {
      int answer = -1;
      int MAX_RANGE = 9;
      int MIN_RANGE = 1;
      Set<Integer>[] set = new HashSet[MAX_RANGE];
      int t = N;
      for (int i = MIN_RANGE; i < MAX_RANGE; i++) {
        set[i] = new HashSet<>();
        set[i].add(t);
        t = t * 10 + N;
      }
      for (int i = MIN_RANGE; i < MAX_RANGE; i++) {
        for (int j = 1; j < i; j++) {
          for (int a : set[j]) {
            for (int b : set[i - j]) {
              set[i].add(a + b);
              set[i].add(a - b);
              set[i].add(b - a);
              set[i].add(a * b);
              if (a != 0) {
                set[i].add(b / a);
              }
              if (b != 0) {
                set[i].add(a / b);
              }
            }
          }
        }
      }
      for (int i = MIN_RANGE; i < MAX_RANGE; i++) {
        if (set[i].contains(number)) {
          answer = i;
          break;
        }
      }
      return answer;
    }

  }

  public static void main(String[] args) {
    int N = 5, number = 12;
    new Solution().solution(N, number);
  }
}
// 0, 0, 5, 12 / 5, 0, 1
// -> 1, 5, 5, 12 / 5, 0, 2
// -> 2, 10, 5, 12
// -> 2, 0, 5, 12 -> 5-5
// -> 1, -5, 5, 12