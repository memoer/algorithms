package baekjoon.기본탐색;

import java.util.Scanner;

public class P1668 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    final int N = Integer.parseInt(sc.nextLine());
    int[] arr = new int[N];
    int max = Integer.MIN_VALUE;
    int maxIdx = -1;
    int l = 0;
    int r = 0;

    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(sc.nextLine());
      if (max < arr[i]) {
        max = arr[i];
        maxIdx = i;
        l += 1;
      }
    }
    max = Integer.MIN_VALUE;
    for (int i = N - 1; i >= maxIdx; i--) {
      if (max < arr[i]) {
        max = arr[i];
        r += 1;
      }
    }

    System.out.println(l);
    System.out.println(r);

    sc.close();
  }
}
