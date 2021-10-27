package programmers.search.complete;

import java.util.HashSet;

// 소수 판별 알고리즘 -> 에라토스테네스의 체 사용
public class S42839Review {
  static class Solution {
    private boolean isPrime(int n) {
      if (n < 2) {
        return false;
      } else if (n == 2 || n == 3) {
        return true;
      }
      for (int i = 2; i <= (int) Math.sqrt(n); i++) {
        if (n % i == 0) {
          return false;
        }
      }
      return true;
    }

    private void permutation(String prefix, String s, HashSet<Integer> set) {
      if (!prefix.equals("")) {
        set.add(Integer.valueOf(prefix));
      }
      for (int i = 0; i < s.length(); i++) {
        permutation(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1, s.length()), set);
      }
    }

    public int solution(String numbers) {
      HashSet<Integer> set = new HashSet<>();
      int count = 0;
      permutation("", numbers, set);
      while (set.iterator().hasNext()) {
        int num = set.iterator().next();
        set.remove(num);
        if (isPrime(num)) {
          count += 1;
        }
      }
      return count;
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().solution("17"));
  }
}
