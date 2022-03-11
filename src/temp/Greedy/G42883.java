package temp.Greedy;

public class G42883 {
  static class Solution {
    public String solution(String number, int k) {
      StringBuilder sb = new StringBuilder();
      int l = number.length();
      int idx = 0;
      for (int i = 0; i < l - k; i++) {
        int max = 0;
        for (int j = idx; j < i + k + 1; j++) {
          int n = number.charAt(j) - '0';
          if (n > max) {
            max = n;
            idx = j;
          }
        }
        sb.append(number.charAt(idx++));
      }
      return sb.toString();
    }
  }

  public static void main(String[] args) {
    String number = "1924";
    int k = 2;
    System.out.println(new Solution().solution(number, k));
  }
}

// 4177 2528 41