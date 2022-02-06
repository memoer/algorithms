package baekjoon.기본자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// * 스택, 구현, 그리디
public class P5397 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Stack<Character> ls = new Stack<>();
    Stack<Character> rs = new Stack<>();
    String[] result = new String[n];
    for (int i = 0; i < n; i++) {
      StringBuilder sb = new StringBuilder();
      String str = br.readLine();
      for (char c : str.toCharArray()) {
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
