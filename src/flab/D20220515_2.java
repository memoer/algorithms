package flab;

import java.util.ArrayList;
import java.util.List;

// 괄호 생성하기
// https://docs.google.com/document/d/1jZXdRXlviLadw7HdDuWXMQQnCOScTxU8_ZXRf_ehcxM/edit
public class D20220515_2 {
  public static void main(String[] args) {
    int n = 4;
    List<String> strings = new Solution().generateParenthesis(n);
    for (String string : strings) {
      System.out.println("string = " + string);
    }
    System.out.println(strings.size());
  }

  static class Solution {
    private final List<String> result = new ArrayList<>();
    private final char OPEN = '(';
    private final char CLOSE = ')';
    private int n;

    private void permutation(int opening, int closed, StringBuilder sb, int length) {
      if (this.n == closed) {
        if (0 == opening) {
          result.add(sb.toString());
        }
        return;
      }

      if (opening != n) {
        sb.append(this.OPEN);
        permutation(opening + 1, closed, sb, length + 1);
        sb.deleteCharAt(length);
      }

      if (opening != 0) {
        sb.append(this.CLOSE);
        permutation(opening - 1, closed + 1, sb, length + 1);
        sb.deleteCharAt(length);
      }
    }

    public List<String> generateParenthesis(int n) {
      this.n = n;
      StringBuilder sb = new StringBuilder();
      sb.append(this.OPEN);
      permutation(1, 0, sb, 1);
      return result;
    }
  }
}
