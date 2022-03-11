package temp.b;

import java.util.Scanner;

public class P1543 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StringBuilder document = new StringBuilder(sc.nextLine());
    final String FIND = sc.nextLine();
    final int FIND_LENGTH = FIND.length();
    int result = 0;
    while (true) {
      int idx = document.indexOf(FIND);
      if (idx == -1) {
        break;
      }
      result += 1;
      document.delete(0, idx + FIND_LENGTH);
    }
    System.out.println(result);
    sc.close();
  }
}
