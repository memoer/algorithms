package flab;

import java.util.LinkedList;
import java.util.Queue;

public class D20220326_1 {
  public static void main(String[] args) {
    int[] tickets = {2, 3, 2};
    int k = 2;
    System.out.println(new Solution().timeRequiredToBuy(tickets, k));
  }

  static class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
      final int LENGTH = tickets.length;
      int asnwer = 0;
      Queue<Ticket> q = new LinkedList<>();
      for (int i = 0; i < LENGTH; i++) {
        q.offer(new Ticket(tickets[i], i));
      }
      while (!q.isEmpty()) {
        Ticket t = q.poll();
        t.buy();
        asnwer += 1;
        if (t.number != 0) q.offer(t);
        else if (t.idx == k) break;
      }
      return asnwer;
    }

    private static class Ticket {
      public int number;
      public int idx;

      public Ticket(int number, int idx) {
        this.number = number;
        this.idx = idx;
      }

      public void buy() {
        this.number -= 1;
      }
    }
  }
}
