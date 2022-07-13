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
      final int BIT_LIMIT = 2;
      long[] answer = new long[LENGTH];
      // 최대 -> 100,000
      for (int i = 0; i < LENGTH; i++) {
        long num = numbers[i];
        if (num % 2 == 0) answer[i] = num + 1;
        else {
          long next = num + 1;
          int count = Long.bitCount(num ^ next) - BIT_LIMIT;
          long plus = (long) (Math.pow(2, count) - 1);
          answer[i] = next + plus;
        }
      }
      return answer;
    }
  }
}
