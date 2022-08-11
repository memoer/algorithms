package baekjoon.twopointer;

import java.util.Arrays;
import java.util.Scanner;

public class P2230 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    int M = scanner.nextInt();
    int[] arr = new int[N];
    for (int i = 0; i < N; i++) arr[i] = scanner.nextInt();
    Arrays.sort(arr);

    int answer = Integer.MAX_VALUE;
    int left = 0;
    int right = 1;
    int diff;
    while (left < N) {
      diff = arr[right] - arr[left];
      if (diff >= M) {
        answer = Math.min(answer, diff);
        left += 1;
      } else {
        if (right + 1 != N) right += 1;
        else break;
      }
    }

    System.out.println(answer);
  }
}
