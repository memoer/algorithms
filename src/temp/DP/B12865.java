package temp.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B12865 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int[][] dp = new int[n + 1][k + 1];

    for (int i = 1; i < n + 1; i++) {
      st = new StringTokenizer(br.readLine());
      int w = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      for (int j = 1; j < k + 1; j++) {
        if (w <= j) {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    System.out.println(dp[n][k]);
    br.close();
  }
}
