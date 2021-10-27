package baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class P1874 {

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int n = Integer.parseInt(sc.nextLine());
    int last = 0;
    Stack<Integer> s = new Stack<Integer>();
    List<Character> result = new ArrayList<Character>();
    for (int i = 0; i < n; i++) {
      int input = Integer.parseInt(sc.nextLine());
      while (input > last) {
        result.add('+');
        s.push(++last);
      }
      if (s.peek() == input) {
        s.pop();
        result.add('-');
      } else {
        System.out.println("NO");
        System.exit(0);
      }
    }
    for (Character c : result) {
      System.out.println(c);
    }
    sc.close();
  }
}
