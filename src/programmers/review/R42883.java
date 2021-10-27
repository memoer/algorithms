package programmers.review;

public class R42883 {
  static class Solution {
    public String solution(String number, int k) {
      StringBuilder sb = new StringBuilder();
      int idx = 0;
      for (int i = 0; i < number.length() - k; i++) {
        int max = 0;
        for (int j = idx; j < i + k + 1; j++) {
          int num = number.charAt(j) - '0';
          if (num > max) {
            max = num;
            idx = j;
          }
        }
        sb.append(number.charAt(idx++));
      }
      return sb.toString();
    }
  }

  public static void main(String[] args) {
    String number = "4177252841";
    int k = 4;
    System.out.println(new Solution().solution(number, k));
  }
}

// 4177252841