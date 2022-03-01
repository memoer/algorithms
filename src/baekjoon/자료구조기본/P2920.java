package baekjoon.자료구조기본;

import java.util.Scanner;

// * 배열, 구현
public class P2920 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    String[] s = sc.nextLine().split(" ");
    boolean isAscending = false;
    boolean isDescending = false;
    for (int i = 1; i < s.length; i++) {
      char pre = s[i - 1].charAt(0);
      char cur = s[i].charAt(0);
      if (pre < cur) {
        isAscending = true;
      } else if (cur < pre) {
        isDescending = true;
      }
    }
    if (isAscending && isDescending) {
      System.out.println("mixed");
    } else if (isAscending) {
      System.out.println("ascending");
    } else {
      System.out.println("descending");
    }
    sc.close();
  }
}
