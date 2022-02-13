package baekjoon.동적프로그래밍;

import java.util.Scanner;

// LCS -> 다이나믹 프로그래밍중에서도 아주 기초 중의 기초라는데..? 
public class P9251 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String a = sc.nextLine();
    String b = sc.nextLine();
    final int A_LENGTH = a.length();
    final int B_LENGTH = b.length();
    int[][] dp = new int[A_LENGTH + 1][B_LENGTH + 1];
    sc.close();

    for (int i = 1; i < A_LENGTH + 1; i++) {
      char cur = a.charAt(i - 1);
      for (int j = 1; j < B_LENGTH + 1; j++) {
        char temp = b.charAt(j - 1);
        if (cur == temp) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    System.out.println(dp[A_LENGTH][B_LENGTH]);
  }
}
