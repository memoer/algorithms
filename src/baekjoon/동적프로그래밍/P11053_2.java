package baekjoon.동적프로그래밍;

import java.util.Arrays;
import java.util.Scanner;

public class P11053_2 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    final int N = Integer.parseInt(scanner.nextLine());
    int[] s = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    scanner.close();

    int[] dp = new int[N];
    for (int i = 1; i < N; i++) {
      for (int j = 0; j < i; j++) {
        if (s[i] > s[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
      }
    }
    Arrays.stream(dp).max().ifPresent(n -> System.out.println(n + 1));
  }
}
