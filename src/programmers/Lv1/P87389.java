package programmers.Lv1;

public class P87389 {
  static class Solution {
    public int solution(int n) {
      int answer = 0;
      for (int i = n - 1; i >= 1; i--) {
        if (n % i == 1) {
          answer = i;
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().solution(7));
  }
}
