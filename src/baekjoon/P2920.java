package baekjoon;

import java.util.Scanner;

public class P2920 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    Boolean isAscending = false;
    Boolean isDescending = false;
    int pre = sc.nextInt();
    for (int i = 0; i < 7; i++) {
      int input = sc.nextInt();
      if (pre < input) {
        isAscending = true;
      } else {
        isDescending = true;
      }
      pre = input;
    }
    if (isAscending && isDescending) {
      System.out.println("mixed");
    } else if (isDescending) {
      System.out.println("descending");
    } else {
      System.out.println("ascending");
    }
    sc.close();
  }
}
