package baekjoon.자료구조기본;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// * 스택, 그리디
public class P1874 {
  private final static char ADD = '+';
  private final static char SUB = '-';

  public static void print(List<Character> list) {
    for (char c : list) {
      System.out.println(c);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Stack<Integer> s = new Stack<>();
    List<Character> result = new ArrayList<>();
    int n = Integer.valueOf(br.readLine());
    int last = 0;
    for (int i = 0; i < n; i++) {
      int num = Integer.valueOf(br.readLine());
      if (!s.isEmpty() && s.peek() > num) {
        System.out.println("NO");
        System.exit(0);
      }
      while (num > last) {
        s.push(++last);
        result.add(ADD);
      }
      if (s.peek() == num) {
        s.pop();
        result.add(SUB);
      }
    }
    print(result);
    br.close();
  }
}
