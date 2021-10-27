package programmers.review;

public class P43165 {
  static class Solution {
    int answer = 0;
    int target = 0;
    int[] numbers;

    private void dfs(int idx, int sum) {
      if (idx == numbers.length) {
        if (sum == target) {
          answer++;
        }
        return;
      }
      dfs(idx + 1, sum + numbers[idx]);
      dfs(idx + 1, sum - numbers[idx]);
    }

    public int solution(int[] numbers, int target) {
      this.target = target;
      this.numbers = numbers;
      dfs(0, 0);
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] numbers = { 1, 1, 1, 1, 1 };
    int target = 3;
    System.out.println(new Solution().solution(numbers, target));
  }
}
