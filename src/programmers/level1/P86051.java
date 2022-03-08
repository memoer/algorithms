package programmers.level1;

public class P86051 {
  static class Solution {
    public int solution(int[] numbers) {
      int answer = 0;
      boolean[] existed = new boolean[10];
      for (int number : numbers) {
        existed[number] = true;
      }
      for (int i = 0; i < 10; i++) {
        if (!existed[i]) {
          answer += i;
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] numbers = { 1, 2, 3, 4, 6, 7, 8, 0 };
    System.out.println(new Solution().solution(numbers));
  }
}
