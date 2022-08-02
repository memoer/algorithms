package programmers.Lv2;

import java.util.ArrayList;
import java.util.List;

public class P92335 {
  public static void main(String[] args) {
    int n = 437_674;
    int k = 3;
    System.out.println(new Solution().solution(n, k));
  }

  private static class Solution {
    // 1 <= n <= 1,000,000
    public int solution(int n, int k) {
      int answer = 0;
      String[] splitted = Integer.toString(n, k).split("0");
      List<Long> list = new ArrayList<>();
      for (String s : splitted) {
        if (s.length() == 0) continue;
        list.add(Long.parseLong(s));
      }
      for (Long aLong : list) if (isPrime(aLong)) answer += 1;

      return answer;
    }

    private boolean isPrime(long num) {
      if (num == 1) return false;
      for (int i = 2; i <= Math.sqrt(num); i++) if (num % i == 0) return false;
      return true;
    }
  }
}
