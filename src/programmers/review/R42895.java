package programmers.review;

import java.util.HashSet;

public class R42895 {
  static class Solution {

    private void permutation(HashSet<Integer> aSet, HashSet<Integer> bSet, HashSet<Integer> targetSet) {
      for (int a : aSet) {
        for (int b : bSet) {
          targetSet.add(a + b);
          targetSet.add(a - b);
          targetSet.add(b - a);
          targetSet.add(a * b);
          if (b != 0) {
            targetSet.add(a / b);
          }
          if (a != 0) {
            targetSet.add(b / a);
          }
        }
      }
    }

    public int solution(int N, int number) {
      int MAX_ANSWER = 8;
      int answer = -1;
      HashSet<Integer>[] s = new HashSet[MAX_ANSWER];
      int tempN = N;
      for (int i = 0; i < MAX_ANSWER; i++) {
        s[i] = new HashSet<>();
        s[i].add(tempN);
        if (i != 0) {
          for (int j = 0; j < i; j++) {
            HashSet<Integer> aSet = s[j];
            HashSet<Integer> bSet = s[i - j - 1];
            permutation(aSet, bSet, s[i]);
          }
        }
        if (s[i].contains(number)) {
          answer = i + 1;
          break;
        }
        tempN = tempN * 10 + N;
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int N = 8;
    int number = 53;
    System.out.println(new Solution().solution(N, number));
  }
}
