package programmers.level1;

import java.util.HashSet;
import java.util.Set;

public class P68644 {
  static class Solution {
    public int[] solution(int[] numbers) {
      Set<Integer> s = new HashSet<>();
      int length = numbers.length;
      for (int i = 0; i < length - 1; i++) {
        for (int j = i + 1; j < length; j++) {
          s.add(numbers[i] + numbers[j]);
        }
      }
      return s.stream().sorted().mapToInt(m -> m).toArray();
    }
  }

  public static void main(String[] args) {
    int[] numbers = { 2, 1, 3, 4, 1 };
    for (int num : new Solution().solution(numbers)) {
      System.out.print(num + ", ");
    }
    System.out.println();
  }
}
