package programmers.level1;

import java.util.Arrays;

public class P12910 {
  static class Solution {
    public int[] solution(int[] arr, int divisor) {
      int[] result = Arrays.stream(arr).filter(p -> p % divisor == 0).sorted().toArray();
      return result.length == 0 ? new int[] { -1 } : result;
    }
  }

  public static void main(String[] args) {
    int[] arr = { 2, 36, 1, 3 };
    int divisor = 1;
    for (int num : new Solution().solution(arr, divisor)) {
      System.out.print(num + ", ");
    }
    System.out.println();
  }
}
