package baekjoon.자료구조고급;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

// * 해시, 배열, 구현
public class P1920 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    Set<Integer> set = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).boxed()
        .collect(Collectors.toSet());
    final int M = Integer.parseInt(sc.nextLine());
    int[] b = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
    boolean[] result = new boolean[M];
    for (int i = 0; i < b.length; i++) {
      result[i] = set.contains(b[i]);
    }
    for (boolean i : result) {
      System.out.println(i ? '1' : '0');
    }
    sc.close();
  }
}
