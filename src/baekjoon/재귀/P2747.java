package baekjoon.재귀;

import java.util.Scanner;

public class P2747 {

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int answer = 0;
    int a = 1;
    int b = 1;
    int N = Integer.valueOf(sc.nextLine());
    switch (N) {
      case 1:
      case 2:
        answer = 1;
        break;
      case 3:
        answer = a + b;
        break;
      default: {
        int i = 3;
        while (i++ != N) {
          int temp = b;
          b = a + b;
          a = temp;
        }
        answer = a + b;
      }
    }
    System.out.println(answer);
    sc.close();
  }
}
