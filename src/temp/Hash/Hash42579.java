package temp.Hash;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hash42579 {
  static class Solution {
    public class Music implements Comparable<Music> {
      public String genre;
      public int play;
      public int idx;

      public Music(String genre, int play, int idx) {
        this.genre = genre;
        this.play = play;
        this.idx = idx;
      }

      @Override
      public int compareTo(Music o) {
        if (this.idx == o.idx) {
          return this.idx - o.idx;
        }
        return o.play - this.play;
      }
    }

    private int sum(List<Music> musicList) {
      return musicList.stream().mapToInt(m -> m.play).sum();
    }

    public int[] solution(String[] genres, int[] plays) {
      return IntStream.range(0, genres.length).mapToObj(i -> new Music(genres[i], plays[i], i))
          .collect(Collectors.groupingBy(m -> m.genre)).entrySet().stream()
          .sorted((pre, cur) -> this.sum(cur.getValue()) - this.sum(pre.getValue()))
          .flatMap(m -> m.getValue().stream().sorted().limit(2))
          .mapToInt(m -> m.idx).toArray();
    }
  }

  public static void main(String[] args) {
    String[] genres = { "classic", "pop", "classic", "classic", "pop" };
    int[] plays = { 500, 600, 150, 800, 2500 };
    for (int num : new Solution().solution(genres, plays)) {
      System.out.print(num + ",");
    }
    System.out.println();
  }
}
