package baekjoon.탐욕;

import java.util.Scanner;

public class P1439 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();
    char cur = '\0';
    int zero = 0;
    int one = 0;
    sc.close();
    for (char c : str.toCharArray()) {
      if (cur == '\0' || cur != c) {
        if (c == '1') {
          one += 1;
        } else if (c == '0') {
          zero += 1;
        }
        cur = c;
      }
    }
    System.out.println(Math.min(zero, one));
  }
}
