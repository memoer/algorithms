package programmers.highscore.greedy;

public class P42883 {

  public static void main(String[] args) {
    String number = "9876543214";
    int k = 4;
    String solution = new Solution().solution(number, k);
    System.out.println(solution);
  }

  public static class Solution {

    private char[] chars;
//1 3
//11 2
//111 1
//9 0
//
//
//k -> 3, 1+2+3 = 6
//
//k(k+1)/2
//
//
//
//n + k(k+1)/2 -> n + k^2/2 + k/2 -> n + k^2 + k
    public String solution(String number, int k) {
      chars = number.toCharArray();
      int len = number.length();

      StringBuilder sb = new StringBuilder();
      int i = 0;
      for (; i < len && k != 0; i++) {
        int end = Math.min(i + k, len);
        if (isLower(i, end == len ? len - 1 : end)) {
          k -= 1;
        } else {
          sb.append(chars[i]);
        }
      }
      if (i < len) {
        for (; i < len; i++) {
          sb.append(chars[i]);
        }
      }
      return sb.substring(0, sb.length() - k);
    }

    private boolean isLower(int i, int end) {
      boolean isLower = false;
      char target = chars[i];
      if (target == '9') {
        return false;
      }

      for (; i <= end; i++) {
        char diff = chars[i];
        if (target < diff) {
          return true;
        }
      }
      return isLower;
    }
  }
}