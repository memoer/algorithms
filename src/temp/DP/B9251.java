package temp.DP;

import java.util.Scanner;

public class B9251 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String a = sc.nextLine();
    String b = sc.nextLine();
    int max = 0;
    int[][] dp = new int[a.length() + 1][b.length() + 1];
    sc.close();

    for (int i = 1; i <= a.length(); i++) {
      char charA = a.charAt(i - 1);
      for (int j = 1; j <= b.length(); j++) {
        char charB = b.charAt(j - 1);
        if (charA == charB) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
        }
        max = Math.max(max, dp[i][j]);
      }
    }

    System.out.println(max);
  }
}
