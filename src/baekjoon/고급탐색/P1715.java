package baekjoon.고급탐색;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P1715 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    final int N = Integer.parseInt(sc.nextLine());
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int result = 0;
    for (int i = 0; i < N; i++) {
      pq.add(Integer.parseInt(sc.nextLine()));
    }
    while (pq.size() > 1) {
      int temp = pq.poll() + pq.poll();
      result += temp;
      pq.add(temp);
    }
    System.out.println(result);
    sc.close();
  }
}
