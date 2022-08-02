package programmers.Lv2;

public class P12924 {
  public static void main(String[] args) {
    int n = 15;
    System.out.println(new Solution().solution(n));
  }

  private static class Solution {
    // 1 <= n <= 10,000
    public int solution(int n) {
      int answer = 1;
      int limit = n / 2 + (n % 2 == 0 ? 0 : 1);
      for (int i = 1; i < limit; i++) {
        int sum = i;
        for (int j = i + 1; sum < n && j <= limit; j++) sum += j;
        if (sum == n) answer += 1;
      }
      return answer;
    }
  }
}
