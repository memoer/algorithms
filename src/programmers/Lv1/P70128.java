package programmers.Lv1;

public class P70128 {
  static class Solution {
    public int solution(int[] a, int[] b) {
      int answer = 0;
      int length = a.length;
      for (int i = 0; i < length; i++) {
        answer += (a[i] * b[i]);
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 3, 4 };
    int[] b = { -3, -1, 0, 2 };
    System.out.println(new Solution().solution(a, b));
  }
}
