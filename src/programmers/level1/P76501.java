package programmers.level1;

public class P76501 {
  static class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
      int answer = 0;
      int length = absolutes.length;
      for (int i = 0; i < length; i++) {
        answer += (signs[i] ? absolutes[i] : -absolutes[i]);
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] absolutes = { 4, 7, 12 };
    boolean[] signs = { true, false, true };
    System.out.println(new Solution().solution(absolutes, signs));
  }
}
