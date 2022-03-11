package temp.b;

import java.util.Scanner;

public class P1427 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StringBuilder sb = new StringBuilder();
    int[] temp = new int[10];
    for (char c : sc.nextLine().toCharArray()) {
      temp[c - '0'] += 1;
    }
    for (int i = 9; i >= 0; i--) {
      for (int j = 0; j < temp[i]; j++) {
        sb.append(i);
      }
    }
    System.out.println(sb.toString());
    sc.close();
  }
}
