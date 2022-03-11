package temp.DP;

import java.util.Scanner;
import java.util.StringTokenizer;

public class B1495 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StringTokenizer st = new StringTokenizer(sc.nextLine());
    int n = Integer.parseInt(st.nextToken());
    int start = Integer.parseInt(st.nextToken());
    int limit = Integer.parseInt(st.nextToken());
    boolean[][] dp = new boolean[n + 1][limit + 1];
    int result = -1;
    dp[0][start] = true;

    for (int i = 1; i < n + 1; i++) {
      int v = sc.nextInt();
      for (int j = 0; j < limit + 1; j++) {
        if (!dp[i - 1][j]) {
          continue;
        }
        int sum = j + v;
        int sub = j - v;
        if (sum <= limit && sum >= 0) {
          dp[i][sum] = true;
        }
        if (sub <= limit && sub >= 0) {
          dp[i][sub] = true;
        }
      }
    }
    sc.close();

    for (int i = limit; i >= 0; i--) {
      if (dp[n][i]) {
        result = i;
        break;
      }
    }
    System.out.println(result);
  }
}
