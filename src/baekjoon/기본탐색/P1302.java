package baekjoon.기본탐색;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class P1302 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    final int N = Integer.parseInt(sc.nextLine());
    Map<String, Integer> map = new HashMap<>();

    for (int i = 0; i < N; i++) {
      String book = sc.nextLine();
      map.put(book, map.getOrDefault(book, 1) + 1);
    }
    String result = map.entrySet().stream().sorted((pre, cur) -> {
      if (pre.getValue() == cur.getValue()) {
        return pre.getKey().compareTo(cur.getKey());
      }
      return cur.getValue() - pre.getValue();
    }).iterator().next().getKey();
    System.out.println(result);

    sc.close();
  }
}
