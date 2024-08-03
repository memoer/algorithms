package programmers.highscore.fullsearch;

import java.util.HashSet;
import java.util.Set;

public class P42839 {

  public static void main(String[] args) {
    new Solution().solution("011");
  }

  private static class Solution {

    private Set<Integer> set = new HashSet<>();
    private String[] split;
    private int len;

    public int solution(String numbers) {
      split = numbers.split("");
      len = numbers.length();

      int max = len + 1;
      StringBuilder sb = new StringBuilder();
      boolean[] visited = new boolean[len];
      for (int digit = 1; digit < max; digit++) {
        permutation(digit, sb, visited);
      }

      int answer = 0;
      for (Integer n : set) {
        if (isPrime(n)) {
          answer += 1;
        }
      }

      return answer;
    }

    private void permutation(int digit, StringBuilder sb, boolean[] visited) {
      if (sb.length() == digit) {
        Integer n = Integer.valueOf(sb.toString());
        set.add(n);
        return;
      }

      for (int i = 0; i < len; i++) {
        if (visited[i]) {
          continue;
        }
        sb.append(split[i]);
        visited[i] = true;
        permutation(digit, sb, visited);
        sb.deleteCharAt(sb.length() - 1);
        visited[i] = false;
      }
    }

    private boolean isPrime(int n) {
      if (n <= 1) {
        return false;
      }

      int end = (int) Math.sqrt(n);
      for (int i = 2; i <= end; i++) {
        if (n % i == 0) {
          return false;
        }
      }
      return true;
    }
  }
}