package programmers.Lv2;

import java.util.HashSet;
import java.util.Set;

public class P67257 {
  static class Solution {
    private Set<String[]> set;
    private String[] op;
    private long answer;

    private void initialize() {
      String[] template = { "\\*", "\\+", "\\-" };
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
            set.add(new String[] { template[i], template[j], template[k] });
          }
        }
      }
    }

    private long getSum(String ex, int idx) {
      String[] splitted = ex.split(op[idx]);
      int length = splitted.length;

      if (idx == 0) {
        long sum = Long.parseLong(splitted[0]);
        for (int i = 1; i < length; i++) {
          sum = calculate(op[idx], sum, Long.parseLong(splitted[i]));
        }
        return sum;
      }

      long sum = getSum(splitted[0], idx - 1);
      for (int i = 1; i < length; i++) {
        sum = calculate(op[idx], sum, getSum(splitted[i], idx - 1));
      }
      return sum;
    }

    private long calculate(String operator, long sum, long num2) {
      return switch (operator) {
        case "\\+" -> sum + num2;
        case "\\-" -> sum - num2;
        case "\\*" -> sum * num2;
        default -> throw new UnsupportedOperationException();
      };
    }

    public long solution(String expression) {
      initialize();
      for (String[] op : set) {
        this.op = op;
        long sum = getSum(expression, 2);
        answer = Math.max(answer, Math.abs(sum));
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    String expression = "100-200*300-500+20";
    System.out.println(new Solution().solution(expression));
  }
}
