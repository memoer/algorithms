package programmers.review;

import java.util.HashSet;

public class R42839 {
  static class Solution {
    HashSet<Integer> set = new HashSet<>();

    private boolean isPrime(int num) {
      if (num < 2) {
        return false;
      } else if (num == 2 || num == 3) {
        return true;
      }
      for (int i = 2; i <= Math.sqrt(num); i++) {
        if (num % i == 0) {
          return false;
        }
      }
      return true;
    }

    private void permutation(String prefix, String numbers) {
      if (!prefix.equals("")) {
        set.add(Integer.valueOf(prefix));
      }
      for (int i = 0; i < numbers.length(); i++) {
        permutation(prefix + numbers.charAt(i), numbers.substring(0, i) + numbers.substring(i + 1, numbers.length()));
      }
    }

    public int solution(String numbers) {
      int answer = 0;
      permutation("", numbers);
      for (int num : set) {
        System.out.println(num);
        if (isPrime(num)) {
          answer++;
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    String numbers = "011";
    System.out.println(new Solution().solution(numbers));
  }
}

// 0, 1, 1