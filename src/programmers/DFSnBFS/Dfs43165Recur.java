package programmers.DFSnBFS;

public class Dfs43165Recur {
  public static void main(String[] args) {
    int[] numbers = { 1, 1, 1, 1, 1 };
    int target = 3;
    System.out.println(new Solution().solution(numbers, target));
  }

  static class Solution {
    private int result = 0;

    private void dfs(int[] numbers, int target, int acc, int curIdx) {
      if (curIdx == numbers.length) {
        if (acc == target) {
          result += 1;
        }
        return;
      }
      dfs(numbers, target, acc + numbers[curIdx], curIdx + 1);
      dfs(numbers, target, acc - numbers[curIdx], curIdx + 1);
    }

    public int solution(int[] numbers, int target) {
      dfs(numbers, target, 0, 0);
      return result;
    }
  }
}
