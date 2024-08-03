package programmers.highscore.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P42579 {

  public static void main(String[] args) {
    String[] genres = new String[]{"classic", "pop", "classic", "classic", "pop"};
    int[] plays = new int[]{500, 600, 150, 800, 2500};
    new Solution().solution(genres, plays);
  }

  private static class Solution {

    private Map<String, Integer> report = new HashMap<>();
    private Map<String, List<Song>> map = new HashMap<>();

    private static class Song {

      int idx;
      int plays;
      String genre;

      public Song(int idx, int plays, String genre) {
        this.idx = idx;
        this.plays = plays;
        this.genre = genre;
      }
    }

    public int[] solution(String[] genres, int[] plays) {
//      1. 속한 노래가 많이 재생된 장르를 먼저
//      2. 장르 내 많이 재생된 노래를 먼저
//      3. 장르 내 동일한 재생된 노래 중에서는 고유 번호가 낮은 노래를 먼저
      initialize(genres, plays);
      return map.entrySet()
          .stream()
          .sorted((pre, cur) -> report.get(cur.getKey()) - report.get(pre.getKey()))
          .flatMap(e -> e.getValue()
              .stream()
              .sorted((pre, cur) -> pre.plays == cur.plays ? pre.idx - cur.idx : cur.plays - pre.plays)
              .limit(2)
          )
          .mapToInt(v -> v.idx)
          .toArray();
    }

    private void initialize(String[] genres, int[] plays) {
      int len = genres.length;
      for (int i = 0; i < len; i++) {
        String genre = genres[i];
        int play = plays[i];

        if (!map.containsKey(genre)) {
          map.put(genre, new ArrayList<>());
        }
        map.get(genre).add(new Song(i, play, genre));
        report.put(genre, report.getOrDefault(genre, 0) + play);
      }
    }
  }
}
