package baekjoon.twopointer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P1644 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int num = scanner.nextInt();
    scanner.close();

    List<Integer> list = new ArrayList<>();
    for (int i = 2; i <= num; i++) if (isPrime(i)) list.add(i);

    final int SIZE = list.size();
    int start = 0;
    int end = 0;
    int sum = 0;
    int answer = 0;

    int cnt = 0;

    while (start < SIZE) {
      cnt += 1;
      if (sum == num) {
        answer += 1;
        sum -= list.get(start++);
      } else if (sum > num) {
        sum -= list.get(start++);
      } else {
        if (end != SIZE) sum += list.get(end++);
        else break;
      }
    }
    System.out.println(SIZE+", "+cnt);
    System.out.println(answer);
  }

  private static boolean isPrime(int num) {
    if (num == 1 || num == 2 || num == 3) return true;
    int sqrt = (int) Math.sqrt(num);
    for (int i = 2; i <= sqrt; i++) if (num % i == 0) return false;
    return true;
  }
}
