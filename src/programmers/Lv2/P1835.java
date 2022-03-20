package programmers.Lv2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P1835 {
  public static void main(String[] args) {
    int n = 2;
    String[] data = { "N~F=0", "R~T>2" };
    // String[] data = { "M~C<2", "C~M>1" };
    System.out.println(new Solution().solution(n, data));
  }

  static class Solution {
    int answer;
    char[] f;
    Map<Character, List<Condition>> condition;
    Map<Character, Integer> order;

    public int solution(int n, String[] data) {
      f = new char[] { 'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T' };
      condition = new HashMap<>();
      order = new HashMap<>();

      for (char ch : f) {
        condition.put(ch, new ArrayList<>());
        order.put(ch, 0);
      }
      for (String d : data) {
        char a = d.charAt(0);
        char b = d.charAt(2);
        char op = d.charAt(3);
        int num = (d.charAt(4) - '0');
        condition.get(a).add(new Condition(b, op, num));
        condition.get(b).add(new Condition(a, op, num));
      }

      answer = 0;
      backTracking(1);
      System.out.println(answer);
      return answer;
    }

    private void backTracking(int curLocation) {
      if (curLocation > f.length) {
        answer += 1;
        return;
      }
      for (char candidate : f) {
        if (isAvailable(candidate, curLocation)) {
          order.put(candidate, curLocation);
          backTracking(curLocation + 1);
          order.put(candidate, 0);
        }
      }
    }

    private boolean visited(char candidate) {
      return order.get(candidate) != 0;
    }

    private boolean isAvailable(char candidate, int curLocation) {
      if (visited(candidate)) {
        return false;
      }
      for (Condition c : condition.get(candidate)) {
        if (order.get(c.other) == 0) {
          return true;
        }
        switch (c.op) {
          case '=':
            if (curLocation - order.get(c.other) != c.num) {
              return false;
            }
            break;
          case '<':
            if (curLocation - order.get(c.other) >= c.num) {
              return false;
            }
            break;
          case '>':
            if (curLocation - order.get(c.other) <= c.num) {
              return false;
            }
            break;
          default:
            throw new UnsupportedOperationException();
        }
      }
      return true;
    }

    class Condition {
      public char other;
      public char op;
      public int num;

      public Condition(char other, char op, int num) {
        this.other = other;
        this.op = op;
        this.num = num + 1;
      }
    }
  }
}
