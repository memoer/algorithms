package programmers.highscore.fullsearch;

import java.util.Comparator;
import java.util.List;

public class P42840 {

  public static void main(String[] args) {

  }

  private static class Solution {

    private class Data {

      int idx;
      int acc = 0;

      public Data(int idx) {
        this.idx = idx;
      }
    }

    public int[] solution(int[] answers) {
      int[] a = new int[]{1, 2, 3, 4, 5};
      int[] b = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
      int[] c = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
      Data q = new Data(1), w = new Data(2), e = new Data(3);
      List<Data> result = List.of(q, w, e);

      int len = answers.length;
      for (int i = 0; i < len; i++) {
        int answer = answers[i];
        if (answer == a[i % 5]) {
          q.acc += 1;
        }
        if (answer == b[i % 8]) {
          w.acc += 1;
        }
        if (answer == c[i % 10]) {
          e.acc += 1;
        }
      }

      int max = Math.max(q.acc, Math.max(w.acc, e.acc));
      return result
          .stream()
          .filter(v -> v.acc != max)
          .sorted(Comparator.comparingInt(pre -> pre.idx))
          .mapToInt(v -> v.idx).toArray();
    }
  }
}
