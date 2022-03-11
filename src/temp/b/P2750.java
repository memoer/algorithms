package temp.b;

import java.util.Arrays;
import java.util.Scanner;

public class P2750 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = Integer.parseInt(sc.nextLine());
    int[] list = new int[N];
    for (int i = 0; i < N; i++) {
      list[i] = Integer.parseInt(sc.nextLine());
    }
    Arrays.sort(list);
    for (int num : list) {
      System.out.println(num);
    }
    sc.close();
  }
}
