package temp.DP;

import java.util.Arrays;
import java.util.Scanner;

public class B11053 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = Integer.parseInt(sc.nextLine());
    int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int[] dp = new int[n];
    int max = 0;

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[i] > arr[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }

    for (int i = 0; i < n; i++) {
      max = Math.max(max, dp[i]);
    }

    System.out.println(max + 1);
  }
}
