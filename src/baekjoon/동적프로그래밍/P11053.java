package baekjoon.동적프로그래밍;

import java.util.Arrays;
import java.util.Scanner;

public class P11053 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    final int N = Integer.parseInt(sc.nextLine());
    int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(m -> Integer.parseInt(m)).toArray();
    int[] dp = new int[N];
    int result = 0;

    for (int i = 1; i < N; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[j] < arr[i]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      result = Math.max(result, dp[i]);
    }

    System.out.println(result + 1);
    sc.close();
  }
}