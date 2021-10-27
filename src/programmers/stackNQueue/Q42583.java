package programmers.stackNQueue;

import java.util.LinkedList;
import java.util.Queue;

public class Q42583 {
  static class Solution {
    class Truck {
      int weight;
      int move = 0;

      public Truck(int weight) {
        this.weight = weight;
      }

      public void moving() {
        this.move++;
      }
    }

    public int solution(int bridgeLength, int weight, int[] truckWeights) {
      Queue<Truck> moveQ = new LinkedList<>();
      Queue<Truck> waitQ = new LinkedList<>();
      int answer = 0;
      int curWeight = 0;
      for (int w : truckWeights) {
        waitQ.offer(new Truck(w));
      }
      while (!moveQ.isEmpty() || !waitQ.isEmpty()) {
        if (!moveQ.isEmpty() && moveQ.peek().move >= bridgeLength) {
          Truck t = moveQ.poll();
          curWeight -= t.weight;
        }
        if (!waitQ.isEmpty() && waitQ.peek().weight + curWeight <= weight) {
          Truck t = waitQ.poll();
          curWeight += t.weight;
          moveQ.offer(t);
        }
        for (Truck t : moveQ) {
          t.moving();
        }
        answer += 1;
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int bridgeLength = 100, weight = 100;
    int[] truckWeights = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
    System.out.println(new Solution().solution(bridgeLength, weight, truckWeights));
  }
}
