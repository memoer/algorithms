package baekjoon.탐욕;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P1461 {
  private static int result = 0;

  private static void locateLastBook(PriorityQueue<Integer> pq, int M) {
    result += pq.poll();
    for (int i = 0; i < M - 1; i++) {
      if (pq.isEmpty()) {
        return;
      }
      pq.poll();
    }
  }

  private static boolean locateBook(PriorityQueue<Integer> pq, int M) {
    result += (pq.poll() * 2);
    for (int i = 0; i < M - 1; i++) {
      if (pq.isEmpty()) {
        return false;
      }
      pq.poll();
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    final int N = sc.nextInt();
    final int M = sc.nextInt();
    PriorityQueue<Integer> minus = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder());
    for (int i = 0; i < N; i++) {
      int num = sc.nextInt();
      if (num > 0) {
        plus.offer(num);
      } else {
        minus.offer(Math.abs(num));
      }
    }

    if (!minus.isEmpty() && !plus.isEmpty()) {
      locateLastBook(minus.peek() > plus.peek() ? minus : plus, M);
    } else if (!minus.isEmpty()) {
      locateLastBook(minus, M);
    } else {
      locateLastBook(plus, M);
    }
    while (!minus.isEmpty() && locateBook(minus, M)) {
    }
    while (!plus.isEmpty() && locateBook(plus, M)) {
    }

    System.out.println(result);
  }
}
