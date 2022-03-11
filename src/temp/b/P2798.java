package temp.b;

import java.util.Scanner;

public class P2798 {
  private static int[] getCardList(Scanner sc, int N) {
    int[] cardList = new int[N];
    for (int i = 0; i < N; i++) {
      cardList[i] = sc.nextInt();
    }
    return cardList;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    final int N = sc.nextInt();
    final int M = sc.nextInt();
    int result = Integer.MIN_VALUE;
    int[] cardList = getCardList(sc, N);

    for (int i = 0; i < N - 2; i++) {
      for (int j = i + 1; j < N - 1; j++) {
        for (int k = j + 1; k < N; k++) {
          int sum = cardList[i] + cardList[j] + cardList[k];
          if (sum > M) {
            continue;
          } else {
            result = Math.max(result, sum);
          }
        }
      }
    }
    System.out.println(result);
    sc.close();
  }
}
