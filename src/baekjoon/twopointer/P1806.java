package baekjoon.twopointer;

import java.util.Scanner;

public class P1806 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    int S = scanner.nextInt();
    int[] arr = new int[N];
    for (int i = 0; i < N; i++) arr[i] = scanner.nextInt();
    scanner.close();

    int answer = Integer.MAX_VALUE;
    int left = 0;
    int right = 0;
    int sum = 0;
    while (left < N) {
      if (sum >= S) {
        answer = Math.min(answer, right - left);
        sum -= arr[left++];
      } else {
        if (right != N) sum += arr[right++];
        else break;
      }
    }

    System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
  }
}
