package programmers.Lv2;

public class P77885 {
  public static void main(String[] args) {
    long[] numbers = {2, 7};
    for (long l : new Solution().solution(numbers)) {
      System.out.println(l);
    }
  }

  private static class Solution {
    /*
    * 1 <= numbers.length <= 100,000
     */
    public long[] solution(long[] numbers) {
      final int LENGTH = numbers.length;
      long[] answer = new long[LENGTH];
      // 최대 -> 100,000
      for (int i = 0; i < LENGTH; i++) {
        long num1 = numbers[i];
        // 1,000
        for (long num2 = num1 + 1; ; num2++) {
          String bit = Long.toBinaryString(num1 ^ num2);
          if (isAvailable(bit)) {
            answer[i] = num2;
            break;
          }
        }
      }
      return answer;
    }

    private boolean isAvailable(String bit) {
      int acc = 0;
      for (char ch : bit.toCharArray()) {
        if (ch == '1') acc += 1;
        if (acc >= 3) return false;
      }
      return true;
    }
  }
}
