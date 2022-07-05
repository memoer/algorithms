package baekjoon.동적프로그래밍;

import java.util.Scanner;

public class P9251_2 {
 // Reference Link - https://st-lab.tistory.com/139
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String a = scanner.nextLine();
    String b = scanner.nextLine();
    scanner.close();

    int aLength = a.length();
    int bLength = b.length();
    int[][] dp = new int[aLength + 1][bLength + 1];

    for (int i = 1; i < aLength + 1; i++) {
      char m = a.charAt(i - 1);
      for (int j = 1; j < bLength + 1; j++) {
        char n = b.charAt(j - 1);
        if (m == n) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    System.out.println(dp[aLength][bLength]);
  }
}
