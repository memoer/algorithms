package programmers.Lv2;

public class P43165 {
  static class Solution {
    private int[] numbers;
    private int length;
    private int target;
    private int answer = 0;

    private void dfs(int cnt, int acc) {
      if (cnt == length - 1) {
        if (acc == target) {
          answer += 1;
        }
        return;
      }
      dfs(cnt + 1, acc + numbers[cnt + 1]);
      dfs(cnt + 1, acc - numbers[cnt + 1]);
    }

    public int solution(int[] numbers, int target) {
      this.numbers = numbers;
      this.length = numbers.length;
      this.target = target;
      dfs(0, numbers[0]);
      dfs(0, -numbers[0]);
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] numbers = { 4, 1, 2, 1 };
    int target = 4;
    System.out.println(new Solution().solution(numbers, target));
  }
}
