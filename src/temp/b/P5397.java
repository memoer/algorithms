package temp.b;

import java.util.Scanner;
import java.util.Stack;

public class P5397 {

  private static String getResult(Stack<Character> ls, Stack<Character> rs) {
    StringBuilder sb = new StringBuilder();
    while (!ls.isEmpty()) {
      sb.append(ls.pop());
    }
    sb.reverse();
    while (!rs.isEmpty()) {
      sb.append(rs.pop());
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Stack<Character> ls = new Stack<>();
    Stack<Character> rs = new Stack<>();
    final int N = Integer.valueOf(sc.nextLine());

    for (int i = 0; i < N; i++) {
      String pwd = sc.nextLine();
      for (char c : pwd.toCharArray()) {
        switch (c) {
          case '<':
            if (!ls.isEmpty()) {
              rs.push(ls.pop());
            }
            break;
          case '>':
            if (!rs.isEmpty()) {
              ls.push(rs.pop());
            }
            break;
          case '-':
            if (!ls.isEmpty()) {
              ls.pop();
            }
            break;
          default:
            ls.push(c);
            break;
        }
      }
      System.out.println(getResult(ls, rs));
    }
    sc.close();
  }
}
