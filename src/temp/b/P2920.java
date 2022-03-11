package temp.b;

import java.util.Scanner;

public class P2920 {
  public static void main(String[] args) {
    final int N = 8;
    boolean isAscending = false;
    boolean isDescending = false;
    Scanner sc = new Scanner(System.in);
    String[] s = sc.nextLine().split(" ");
    String result = null;
    for (int i = 1; i < N; i++) {
      int a = Integer.valueOf(s[i - 1]);
      int b = Integer.valueOf(s[i]);
      if (a < b) {
        isAscending = true;
      } else {
        isDescending = true;
      }
    }
    if (isAscending && isDescending) {
      result = "mixed";
    } else if (isAscending) {
      result = "ascending";
    } else {
      result = "descending";
    }
    System.out.println(result);
    sc.close();
  }
}
