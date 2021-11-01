package baekjoon.basicSort;

import java.util.Scanner;

public class P1427 {

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    StringBuilder sb = new StringBuilder();
    int[] temp = new int[10];
    for (char c : sc.nextLine().toCharArray()) {
      temp[c - '0']++;
    }
    for (int i = temp.length - 1; i >= 0; i--) {
      for (int j = 0; j < temp[i]; j++) {
        sb.append(i);
      }
    }
    System.out.println(sb.toString());
    sc.close();
  }
}
