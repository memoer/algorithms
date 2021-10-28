package baekjoon.advancedStructure;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

// * 해시, 배열, 구현
public class P1920 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    String N = sc.nextLine();
    Set<Integer> set = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).boxed()
        .collect(Collectors.toSet());
    int M = Integer.valueOf(sc.nextLine());
    int[] b = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
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
