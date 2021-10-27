package programmers.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class R42579 {
  static class Solution {
    class Music {
      String g;
      int p;
      int i;

      public Music(String g, int p, int i) {
        this.g = g;
        this.p = p;
        this.i = i;
      }
    }

    public int sum(List<Music> l) {
      int sum = 0;
      for (Music m : l) {
        sum += m.p;
      }
      return sum;
    }

    public int[] solution(String[] genres, int[] plays) {
      Map<String, List<Music>> map = new HashMap<>();
      for (int i = 0; i < genres.length; i++) {
        String g = genres[i];
        int p = plays[i];
        if (!map.containsKey(g)) {
          map.put(g, new ArrayList<>());
        }
        map.get(g).add(new Music(g, p, i));
      }
      return map.entrySet().stream().sorted((pre, cur) -> sum(cur.getValue()) - sum(pre.getValue()))
          .flatMap(m -> m.getValue().stream().sorted((pre, cur) -> cur.p - pre.p).limit(2)).mapToInt(m -> m.i)
          .toArray();
    }
  }

  public static void main(String[] args) {
    String[] genres = { "classic", "pop", "classic", "classic", "pop" };
    int[] plays = { 500, 600, 150, 800, 2500 };
    for (int i : new Solution().solution(genres, plays)) {
      System.out.println("i: " + i);
    }
  }
}
