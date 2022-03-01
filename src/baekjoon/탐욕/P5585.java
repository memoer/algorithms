package baekjoon.탐욕;

import java.util.Scanner;

public class P5585 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int[] arr = { 500, 100, 50, 10, 5, 1 };
    int money = 1000 - Integer.parseInt(sc.nextLine());
    int result = 0;
    sc.close();

    for (int coin : arr) {
      result += money / coin;
      money %= coin;
    }
    System.out.println(result);
  }
}
