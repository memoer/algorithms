package programmers.stackNQueue;

import java.util.LinkedList;
import java.util.Queue;

public class Q42583Ohter {
  static class Solution {
    class Truck {
      int weight;
      int move;

      public Truck(int weight) {
        this.weight = weight;
      }

      public void moving() {
        this.move += 1;
      }
    }

    public int solution(int bridgeLength, int weight, int[] truckWeights) {
      Queue<Truck> moveQ = new LinkedList<>();
      Queue<Truck> waitQ = new LinkedList<>();
      int curWeight = 0;
      int answer = 0;
      for (int w : truckWeights) {
        waitQ.offer(new Truck(w));
      }
      // 하나라도 empty라면 종료
      while (!waitQ.isEmpty() || !moveQ.isEmpty()) {
        answer++;
        if (moveQ.isEmpty()) {
          Truck t = waitQ.poll();
          curWeight += t.weight;
          moveQ.offer(t);
          continue;
        }
        for (Truck t : moveQ) {
          t.moving();
        }
        if (moveQ.peek().move >= bridgeLength) {
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
    int bridgeLength = 2, weight = 10;
    int[] truckWeights = { 7, 4, 5, 6 };
    System.out.println(new Solution().solution(bridgeLength, weight, truckWeights));
  }
}
