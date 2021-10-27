package programmers.review;

import java.util.LinkedList;
import java.util.Queue;

public class R42583 {
  static class Solution {
    class Truck {
      int weight;
      int move;

      public Truck(int weight) {
        this.weight = weight;
        this.move = 1;
      }

      public void moving() {
        this.move++;
      }
    }

    public int solution(int bridgeLength, int weight, int[] truckWeights) {
      int answer = 0;
      int curWeight = 0;
      Queue<Truck> moveQ = new LinkedList<>();
      Queue<Truck> waitQ = new LinkedList<>();
      for (int w : truckWeights) {
        waitQ.offer(new Truck(w));
      }
      while (!moveQ.isEmpty() || !waitQ.isEmpty()) {
        answer++;
        for (Truck t : moveQ) {
          t.moving();
        }
        if (!moveQ.isEmpty() && moveQ.peek().move > bridgeLength) {
          Truck t = moveQ.poll();
          curWeight -= t.weight;
        }
        if (!waitQ.isEmpty() && curWeight + waitQ.peek().weight <= weight) {
          Truck t = waitQ.poll();
          curWeight += t.weight;
          moveQ.offer(t);
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int bridgeLength = 2;
    int weight = 10;
    int[] truckWeights = { 7, 4, 5, 6 };
    System.out.println(new Solution().solution(bridgeLength, weight, truckWeights));
  }
}
