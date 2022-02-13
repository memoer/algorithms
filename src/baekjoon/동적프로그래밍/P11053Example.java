package baekjoon.동적프로그래밍;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ! LIS - 가장 긴 증가하는 부분 수열
 * 반례 -> 6 / 1 3 5 2 3 4
 */
public class P11053Example {
  // 출력 값 -> 4
  public static void answer() {
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

  // 출력 값 -> 3
  public static void wrong() {
    Scanner sc = new Scanner(System.in);
    final int N = Integer.parseInt(sc.nextLine());
    int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(m -> Integer.parseInt(m)).toArray();
    int result = 0;

    for (int i = 0; i < N - 1; i++) {
      int max = arr[i];
      int length = 1;
      for (int j = i + 1; j < N; j++) {
        if (arr[j] > max) {
          max = arr[j];
          length += 1;
        }
      }
      result = Math.max(result, length);
    }

    System.out.println(result);
    sc.close();
  }

  public static void main(String[] args) {
    answer();
    // wrong();
  }
}