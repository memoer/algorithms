package programmers.Lv2;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class P67257 {
  static class Solution {
    private Set<String[]> set;
    private String[] candidate;
    private String expression;
    private long answer;

    private void initialize(String expression) {
      final String[] OPERATORS = { "*", "+", "-" };
      this.expression = expression;
      this.answer = 0;
      set = new HashSet<>();
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (i == j) {
            continue;
          }
          for (int k = 0; k < 3; k++) {
            if (j == k || i == k) {
              continue;
            }
            set.add(new String[] { OPERATORS[i], OPERATORS[j], OPERATORS[k] });
          }
        }
      }
    }

    private int calculate(String operator, int num1, int num2) {
      switch (operator) {
        case "+":
          return num1 + num2;
        case "-":
          return num1 - num2;
        case "*":
          return num1 * num2;
        default:
          throw new UnsupportedOperationException();
      }
    }

    private long combination(String ex, int idx) {
      String[] splitted = ex.split(candidate[idx]);
      if (idx == 1) {
        int length = splitted.length;
        long sum = 0;
        for (int i = 0; i < length; i++) {
          sum += calculate(candidate[idx], Integer.valueOf(splitted[i]), Integer.valueOf(splitted[i + 1]));
        }
        return sum;
      }
    }

    public long solution(String expression) {
      initialize(expression);

      return answer;
    }

  }

  public static void main(String[] args) {
    String expression = "100-200*300-500+20";
    System.out.println(new Solution().solution(expression));
  }
}
