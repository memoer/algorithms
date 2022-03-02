package baekjoon.탐욕;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P2212 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int result = 0;
    final int N = sc.nextInt();
    final int K = sc.nextInt();
    int[] coord = new int[N];
    for (int i = 0; i < N; i++) {
      coord[i] = sc.nextInt();
    }
    sc.close();

    Arrays.sort(coord);
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < N - 1; i++) {
      list.add(coord[i + 1] - coord[i]);
    }
    list.sort((pre, cur) -> cur - pre);
    for (int i = 0; i < K - 1; i++) {
      try {
        list.set(i, 0);
      } catch (IndexOutOfBoundsException e) {
        break;
      }
    }
    for (int n : list) {
      result += n;
    }
    System.out.println(result);
  }
}
