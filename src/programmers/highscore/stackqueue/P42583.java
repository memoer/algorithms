package programmers.highscore.stackqueue;

import java.util.LinkedList;
import java.util.Queue;

public class P42583 {

  public static void main(String[] args) {
    int bridgeLength = 100;
    int weight = 100;
    int[] truckWeights = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
    int solution = new Solution().solution(bridgeLength, weight, truckWeights);
    System.out.println(solution);
  }

  private static class Solution {

    private class Truck {

      int weight;
      int distance;

      public Truck(int weight) {
        this.weight = weight;
        this.distance = 0;
      }

      public void move() {
        this.distance += 1;
      }
    }

    public int solution(int bridgeLength, int weight, int[] truckWeights) {
      int answer = 0;
      int sum = 0;
      Queue<Truck> move = new LinkedList<>();
      Queue<Truck> wait = new LinkedList<>();
      for (int truckWeight : truckWeights) {
        wait.offer(new Truck(truckWeight));
      }

      while (!wait.isEmpty() || !move.isEmpty()) {
        if (!move.isEmpty() && move.peek().distance >= bridgeLength) {
          sum -= move.poll().weight;
        }
        if (!wait.isEmpty()) {
          if (sum + wait.peek().weight <= weight) {
            Truck truck = wait.poll();
            move.offer(truck);
            sum += truck.weight;
          }
        }
        if (!move.isEmpty()) {
          for (Truck truck : move) {
            truck.move();
          }
        }
        answer += 1;
      }

      return answer;
    }
  }
}
