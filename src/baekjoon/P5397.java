package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P5397 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Stack<Character> ls = new Stack<Character>();
    Stack<Character> rs = new Stack<Character>();
    String[] result = new String[n];
    for (int i = 0; i < n; i++) {
      String input = br.readLine();
      StringBuilder sb = new StringBuilder();
      for (Character c : input.toCharArray()) {
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
      while (!rs.isEmpty()) {
        ls.push(rs.pop());
      }
      while (!ls.isEmpty()) {
        sb.append(ls.pop());
      }
      result[i] = sb.reverse().toString();
    }
    for (String str : result) {
      System.out.println(str);
    }
    br.close();
  }
}
