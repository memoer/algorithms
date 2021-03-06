package programmers.hash;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class H42579Stream {
  public static class Music implements Comparable<Music> {

    private int played;
    private int id;
    private String genre;

    public Music(String genre, int played, int id) {
      this.genre = genre;
      this.played = played;
      this.id = id;
    }

    @Override
    public int compareTo(Music other) {
      if (this.played == other.played)
        return this.id - other.id;
      return other.played - this.played;
    }

    public String getGenre() {
      return genre;
    }
  }

  public static void main(String[] args) {
    String[] genres = { "classic", "pop", "classic", "tab", "classic", "pop" };
    int[] plays = { 500, 600, 150, 3000, 800, 2500 };
    System.out.println(solution(genres, plays));
  }

  public static int[] solution(String[] genres, int[] plays) {
    // flatMap 없을 시 -> Stream< Entry<String, List<Music>> >
    // flatMap 있을 시 -> Stream< Music >
    return IntStream.range(0, genres.length).mapToObj(i -> new Music(genres[i], plays[i], i))
        .collect(Collectors.groupingBy(Music::getGenre)).entrySet().stream()
        .sorted((a, b) -> sum(b.getValue()) - sum(a.getValue())).flatMap(x -> x.getValue().stream().sorted().limit(2))
        .mapToInt(x -> x.id).toArray();

  }

  private static int sum(List<Music> value) {
    int answer = 0;
    for (Music music : value) {
      answer += music.played;
    }
    return answer;
  }
}