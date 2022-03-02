package baekjoon.탐욕;

import java.util.Arrays;
import java.util.Scanner;

public class P1461 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    final int N = sc.nextInt();
    final int M = sc.nextInt();
    int result = 0;
    int[] location = new int[N];
    boolean last = false;
    for (int i = 0; i < N; i++) {
      location[i] = sc.nextInt();
    }
    Arrays.sort(location);
    for (int i = 0; i < N; i += M) {
      if (i + 1 >= N) {
        result += Math.abs(location[i]);
        last = true;
      } else {
        int a = location[i];
        int b = location[i + 1];
        if (a <= 0 && b <= 0) {
          result += (Math.abs(b) + (b - a) + Math.abs(a));
        } else if (a >= 0 && b >= 0) {
          result += (a + (b - a) + b);
        } else {
          result += (Math.abs(a) + (b - a) + b);
        }
      }
    }
    if (!last) {
      result -= location[N - 1];
    }

    System.out.println(result);
  }
}
