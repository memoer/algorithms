package temp.Search;

import java.util.HashSet;
import java.util.Set;

public class Search42839 {
  static class Solution {
    private Set<Integer> s = new HashSet<>();

    public void permutation(String numbers, String candidate) {
      if (!numbers.equals("")) {
        s.add(Integer.valueOf(numbers));
      }
      if (candidate.equals("")) {
        return;
      }
      int candidateLength = candidate.length();
      for (int i = 0; i < candidateLength; i++) {
        permutation(numbers + candidate.charAt(i),
            candidate.substring(0, i) + candidate.substring(i + 1, candidateLength));
      }
    }

    public boolean isPrime(int number) {
      switch (number) {
        case 0:
        case 1:
          return false;
        case 2:
        case 3:
        case 5:
          return true;
      }
      for (int i = 2; i <= Math.sqrt(number); i++) {
        if (number % i == 0) {
          return false;
        }
      }
      return true;
    }

    public int solution(String numbers) {
      int answer = 0;
      permutation("", numbers);
      for (int number : this.s) {
        if (this.isPrime(number)) {
          answer += 1;
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
