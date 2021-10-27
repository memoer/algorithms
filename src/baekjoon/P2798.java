package baekjoon;

import java.util.Scanner;

public class P2798 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int M = sc.nextInt();
    int MAX_INDEX = N - 1;
    int[] cards = new int[N];
    int max = -1;
    for (int i = 0; i < N; i++) {
      cards[i] = sc.nextInt();
    }
    for (int i = MAX_INDEX; i >= 2; i--) {
      if (cards[i] > M) {
        continue;
      }
      for (int j = i - 1; j >= 1; j--) {
        if (cards[j] > M) {
          continue;
        }
        for (int k = j - 1; k >= 0; k--) {
          if (cards[k] > M) {
            continue;
          }
          int temp = cards[i] + cards[j] + cards[k];
          if (temp > max && temp <= M) {
            max = temp;
          }
        }
      }
    }
    System.out.println(max);
    sc.close();
  }
}
