package temp.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class Queue42583 {
  static class Solution {
    // 다리에는 트럭이 최대 bridgeLength 대 까지 올라갈 수 있다.
    // 다리는 weight 이하까지 무게를 견딜 수 있다.
    public class Truck {
      public int move;
      public int weight;

      public Truck(int weight) {
        this.move = 0;
        this.weight = weight;
      }

      public void moving() {
        this.move += 1;
      }
    }

    public int solution(int bridgeLength, int weight, int[] truckWeights) {
      int answer = 0;
      int totalWeight = 0;
      Queue<Truck> waitQ = new LinkedList<>();
      Queue<Truck> moveQ = new LinkedList<>();
      for (int truckWeight : truckWeights) {
        waitQ.add(new Truck(truckWeight));
      }
      while (!waitQ.isEmpty() || !moveQ.isEmpty()) {
        if (!moveQ.isEmpty() && moveQ.peek().move >= bridgeLength) {
          Truck truck = moveQ.poll();
          totalWeight -= truck.weight;
        }
        if (!waitQ.isEmpty() && waitQ.peek().weight + totalWeight <= weight) {
          Truck truck = waitQ.poll();
          totalWeight += truck.weight;
          moveQ.add(truck);
        }
        moveQ.stream().forEach(t -> t.moving());
        answer += 1;
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
