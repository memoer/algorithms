package programmers.Lv1;

import java.util.stream.IntStream;

public class P42889 {
  public static void main(String[] args) {
    int N = 5;
    int[] stages = { 2, 1, 2, 6, 2, 4, 3, 3 };
    for (int num : new Solution().solution(N, stages)) {
      System.out.print(num + ", ");
    }
    System.out.println();
  }

  static class Solution {
    public int[] solution(int N, int[] stages) {
      int total = stages.length;
      int[] blocked = new int[N + 2];
      int[] challenged = new int[N + 2];
      for (int stage : stages) {
        blocked[stage] += 1;
      }
      for (int i = 1; i < N + 1; i++) {
        challenged[i] = total;
        total -= blocked[i];
      }
      return IntStream.range(1, N + 1)
          .mapToObj(idx -> new Stage(idx, challenged[idx] == 0 ? 0 : (float) blocked[idx] / challenged[idx])).sorted()
          .mapToInt(m -> m.which).toArray();
    }

    private class Stage implements Comparable<Stage> {
      public int which;
      public float rate;

      public Stage(int which, float rate) {
        this.which = which;
        this.rate = rate;
      }

      @Override
      public int compareTo(Stage o) {
        int compare = Float.compare(o.rate, this.rate);
        return compare != 0 ? compare : this.which - o.which;
      }
    }
  }

}
