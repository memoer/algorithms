package programmers.Lv2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P1835 {
  static class Solution {
    private int answer;
    private final char[] PERSON_ARR = { 'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T' };
    private final int PERSON_LENGTH = 8;
    private Map<Character, List<Condition>> condition;
    private Map<Character, Integer> order;

    private void init(String[] data) {
      answer = 0;
      condition = new HashMap<>();
      order = new HashMap<>();
      for (char ch : PERSON_ARR) {
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
    }

    private boolean visited(char candidate) {
      return order.get(candidate) != 0;
    }

    private boolean isAvailable(char candidate, int curLocation) {
      if (visited(candidate)) {
        return false;
      }
      boolean[] checked = new boolean[condition.get(candidate).size()];
      int idx = 0;
      for (Condition c : condition.get(candidate)) {
        if (order.get(c.other) == 0) {
          return true;
        }
        int preLocation = order.get(c.other);
        switch (c.op) {
          case '=':
            checked[idx++] = curLocation - preLocation == c.num;
            break;
          case '<':
            checked[idx++] = curLocation - preLocation < c.num;
            break;
          case '>':
            checked[idx++] = curLocation - preLocation > c.num;
            break;
          default:
            throw new UnsupportedOperationException();
        }
      }
      for (boolean b : checked) {
        if (!b) {
          return false;
        }
      }
      return true;
    }

    private void backTracking(int curLocation) {
      if (curLocation > PERSON_LENGTH) {
        answer += 1;
        return;
      }
      for (char candidate : PERSON_ARR) {
        if (isAvailable(candidate, curLocation)) {
          order.put(candidate, curLocation);
          backTracking(curLocation + 1);
          order.put(candidate, 0);
        }
      }
    }

    public int solution(int n, String[] data) {
      init(data);
      backTracking(1);
      return answer;
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

  public static void main(String[] args) {
    int n = 2;
    String[] data = { "N~F=0", "R~T>2" };
    // String[] data = { "M~C<2", "C~M>1" };
    System.out.println(new Solution().solution(n, data));
  }
}
