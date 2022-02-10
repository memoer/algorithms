package baekjoon.동적프로그래밍;

import java.util.Scanner;

public class P1904 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    final int N = Integer.parseInt(sc.nextLine());
    final int DIV = 15746;
    int a = 1;
    int b = 2;
    if (N < 3) {
      System.out.println(N);
    } else {
      for (int i = 0; i < N - 2; i++) {
        int temp = a;
        a = b;
        b = (temp + a) % DIV;
      }
      System.out.println(b);
    }
    sc.close();
  }
}
