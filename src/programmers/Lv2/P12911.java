package programmers.Lv2;

public class P12911 {
  public static void main(String[] args) {
    int n = 1_000_000;
    System.out.println(new Solution().solution(n));
  }

  private static class Solution {
    /*
    1. 반환 값은 n보다 클 것.
    2. 반환 값의 이진수 "1"의 갯수와 n의 이진수 "1"의 갯수가 동일할 것
    3. 반환 값은 "(1), (2)" 조건을 만족하는 가장 작은 수일 것
     */
    public int solution(int n) {
      int src = Integer.bitCount(n);
      int answer = n + 1;
      for (; ; answer++) {
        int target = Integer.bitCount(answer);
        if (src == target) break;
      }
      return answer;
    }
  }
}
