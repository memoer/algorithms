package baekjoon.기본탐색;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// ? 탐색
public class P1302 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    Map<String, Integer> map = new HashMap<>();
    int N = Integer.valueOf(sc.nextLine());
    for (int i = 0; i < N; i++) {
      String book = sc.nextLine();
      map.put(book, map.getOrDefault(book, 0) + 1);
    }
    String[] answer = map.entrySet().stream().sorted((pre, cur) -> {
      int a = pre.getValue();
      int b = cur.getValue();
      return a != b ? b - a : pre.getKey().compareTo(cur.getKey());
    }).map(x -> x.getKey()).toArray(String[]::new);
    System.out.println(answer[0]);
    sc.close();
  }
}
