package programmers.Lv1;

public class P12921 {

  static class Solution {
    private boolean isPrime(int num) {
      for (int i = 2; i <= Math.sqrt(num); i++) {
        if (num % i == 0) {
          return false;
        }
      }
      return true;
    }

    public int solution(int n) {
      int answer = 0;
      for (int i = 2; i <= n; i++) {
        if (isPrime(i)) {
          answer += 1;
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().solution(5));
  }
}
