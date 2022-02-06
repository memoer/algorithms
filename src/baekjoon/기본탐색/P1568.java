package baekjoon.기본탐색;

import java.util.Scanner;

// ? 탐색
public class P1568 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int N = Integer.valueOf(sc.nextLine());
    int answer = 0;
    int temp = 1;
    sc.close();
    while (N != 0) {
      if (temp <= N) {
        N -= temp++;
        answer++;
        continue;
      }
      temp = 1;
      N -= temp++;
      answer++;
    }
    System.out.println(answer);
  }
}
