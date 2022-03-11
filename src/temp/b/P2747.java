package temp.b;

import java.util.Scanner;

public class P2747 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    final int N = Integer.parseInt(sc.nextLine());
    int a = 1, b = 2;
    int result = 0;
    switch (N) {
      case 0:
        break;
      case 1:
      case 2:
        result = 1;
        break;
      case 3:
        result = 2;
        break;
      default:
        for (int i = 4; i < N; i++) {
          int temp = a;
          a = b;
          b += temp;
        }
        result = a + b;
        break;
    }
    System.out.println(result);
    sc.close();
  }
}
