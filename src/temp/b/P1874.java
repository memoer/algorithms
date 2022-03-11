package temp.b;

import java.util.Scanner;
import java.util.Stack;

public class P1874 {
  public static void main(String[] args) {
    Stack<Integer> s = new Stack<>();
    StringBuilder sb = new StringBuilder();
    int top = 1;
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();

    for (int i = 0; i < N; i++) {
      int num = sc.nextInt();
      for (; top <= num; top++) {
        s.push(top);
        sb.append("+\n");
      }
      if (s.peek() != num) {
        System.out.println("NO");
        System.exit(0);
      }
      s.pop();
      sb.append("-\n");
    }

    System.out.println(sb.toString());
    sc.close();
  }
}
