package baekjoon.기본탐색;

import java.util.Scanner;

// ? 탐색
public class P1668 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int N = Integer.valueOf(sc.nextLine());
    int[] arr = new int[N];
    int[] answer = new int[2];
    int max = 0;
    int maxIdx = -1;
    for (int i = 0; i < N; i++) {
      int num = Integer.valueOf(sc.nextLine());
      arr[i] = num;
      if (i == 0) {
        max = num;
        maxIdx = i;
        answer[0]++;
        continue;
      }
      if (num > max) {
        max = num;
        answer[0]++;
        maxIdx = i;
      }
    }
    max = 0;
    for (int i = N - 1; i >= maxIdx; i--) {
      int num = arr[i];
      if (i == N - 1) {
        max = num;
        answer[1]++;
        continue;
      }
      if (num > max) {
        max = num;
        answer[1]++;
      }
    }
    System.out.println(answer[0]);
    System.out.println(answer[1]);
    sc.close();
  }
}
