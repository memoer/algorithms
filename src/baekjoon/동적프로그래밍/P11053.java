package baekjoon.동적프로그래밍;

import java.util.Scanner;
import java.util.StringTokenizer;

public class P11053 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    final int N = Integer.parseInt(sc.nextLine());
    StringTokenizer st = new StringTokenizer(sc.nextLine());
    int[] dataList = new int[N];
    int[] dp = new int[N];
    int result = Integer.MIN_VALUE;

    for (int i = 0; i < N; i++) {
      dataList[i] = Integer.parseInt(st.nextToken());
      dp[i] = 1;
    }
    for (int i = 1; i < N; i++) {
      for (int j = 0; j < i; j++) {
        if (dataList[i] > dataList[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }

    for (int num : dp) {
      result = Math.max(result, num);
    }
    System.out.println(result);

    sc.close();
  }
}
