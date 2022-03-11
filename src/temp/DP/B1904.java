package temp.DP;

import java.util.Scanner;

public class B1904 {

  public static void main(String[] args) {
    int a = 1;
    int b = 2;
    Scanner sc = new Scanner(System.in);
    final int N = Integer.parseInt(sc.nextLine());
    if (N == 1) {
      System.out.println(a);
    } else if (N == 2) {
      System.out.println(b);
    } else {
      int temp = 0;
      for (int i = 0; i < N - 2; i++) {
        temp = a;
        a = b;
        b = (b + temp) % 15746;
      }
      System.out.println(b);
    }
    sc.close();
  }
}
