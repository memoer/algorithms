package baekjoon.탐색기본;

import java.util.Scanner;

public class P1568 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = Integer.parseInt(sc.nextLine());
    int result = 0;
    int k = 1;

    while (n != 0) {
      if (n < k) {
        k = 1;
      }
      n -= k++;
      result += 1;
    }

    System.out.println(result);

    sc.close();
  }
}
