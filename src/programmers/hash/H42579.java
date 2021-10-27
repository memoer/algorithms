package programmers.hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class H42579 {
  static class Genre {
    String name;
    int play;
    int idx;

    public Genre(String name, int play, int idx) {
      this.name = name;
      this.play = play;
      this.idx = idx;
    }

    @Override
    public String toString() {
      return "name: " + name + ", play: " + play + ", idx: " + idx;
    }
  }

  public static void main(String[] args) throws Exception {
    String[] genres = { "classic", "pop", "classic", "tab", "classic", "pop" };
    int[] plays = { 500, 600, 150, 3000, 800, 2500 };
    System.out.println(solution(genres, plays));
  }

  private static HashMap<String, List<Genre>> getDataMap(String[] genres, int[] plays) {
    HashMap<String, List<Genre>> dataMap = new HashMap<>();
    for (int i = 0; i < genres.length; i++) {
      String name = genres[i];
      int play = plays[i];
      if (!dataMap.containsKey(name)) {
        List<Genre> genreList = new ArrayList<Genre>();
        genreList.add(new Genre(name, play, i));
        dataMap.put(name, genreList);
      } else {
        dataMap.get(name).add(new Genre(name, play, i));
      }
    }
    return dataMap;
  }

  private static void sortDataMap(HashMap<String, List<Genre>> dataMap, String key) {
    Collections.sort(dataMap.get(key), (a, b) -> a.play != b.play ? b.play - a.play : a.idx - b.idx);
  }

  private static void groupByPlay(HashMap<String, Integer> sumGroupByPlay, HashMap<String, List<Genre>> dataMap,
      String key) {
    sumGroupByPlay.put(key, dataMap.get(key).stream().reduce(0, (pre, cur) -> pre + cur.play, Integer::sum));
  }

  private static List<String> sortDataMapNGetSumGroupByPlay(HashMap<String, List<Genre>> dataMap) {
    // { genreName: 해당 장르 플레이 카운트 합산, ... }
    HashMap<String, Integer> sumGroupByPlay = new HashMap<>();
    for (String key : dataMap.keySet()) {
      sortDataMap(dataMap, key);
      groupByPlay(sumGroupByPlay, dataMap, key);
    }
    // 장르별 플레이수 내림차순 정렬
    return sumGroupByPlay.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).map((v) -> v.getKey())
        .collect(Collectors.toList());
  }

  public static List<Integer> solution(String[] genres, int[] plays) {
    List<Integer> answer = new ArrayList<>();
    // { genreName: [ Genre, Genre... ], ... }
    HashMap<String, List<Genre>> dataMap = getDataMap(genres, plays);
    // 장르별 플레이수 내림차순 정렬
    List<String> sortedGenresByPlays = sortDataMapNGetSumGroupByPlay(dataMap);
    for (String genre : sortedGenresByPlays) {
      List<Genre> genreList = dataMap.get(genre);
      answer.add(genreList.get(0).idx);
      if (genreList.size() > 1) {
        answer.add(genreList.get(1).idx);
      }
    }
    return answer;
  }

}
