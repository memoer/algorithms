package programmers.level1;

public class P86051Other {
  static class Solution {
    public int solution(int[] numbers) {
      int answer = 45;
      for (int n : numbers) {
        answer -= n;
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] numbers = { 1, 2, 3, 4, 6, 7, 8, 0 };
    System.out.println(new Solution().solution(numbers));
  }
}
