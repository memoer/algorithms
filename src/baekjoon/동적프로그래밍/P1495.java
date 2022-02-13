package baekjoon.동적프로그래밍;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class P1495 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StringTokenizer st = new StringTokenizer(sc.nextLine());
    final int N = Integer.parseInt(st.nextToken());
    final int START = Integer.parseInt(st.nextToken());
    final int LIMIT = Integer.parseInt(st.nextToken());
    boolean[][] dp = new boolean[N + 1][LIMIT + 1];
    int result = -1;
    int[] dataList = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    dp[0][START] = true;
    sc.close();

    for (int i = 1; i < N + 1; i++) {
      int data = dataList[i - 1];
      for (int j = 0; j < LIMIT + 1; j++) {
        if (!dp[i - 1][j]) {
          continue;
        }
        int a = j - data;
        int b = j + data;
        if (a >= 0 && a <= LIMIT) {
          dp[i][a] = true;
        }
        if (b >= 0 && b <= LIMIT) {
          dp[i][b] = true;
        }
      }
    }
    for (int i = LIMIT; i >= 0; i--) {
      if (dp[N][i]) {
        result = i;
        break;
      }
    }
    System.out.println(result);
  }
}
