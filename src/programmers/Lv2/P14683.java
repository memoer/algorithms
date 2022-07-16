package programmers.Lv2;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class P14683 {
  public static void main(String[] args) {
    String m = "CC#BCC#BCC#BCC#B";
    String[] musicinfos = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
    System.out.println(new Solution().solution(m, musicinfos));
  }

  private static class Solution {
    private Map<String, String> checkScore;

    /* 조건
     * 음은 1분에 1개씩 재생된다.
     * 음악은 반드시 처음부터 재생되며
     * 음악 길이보다 재생된 시간이 길 때는 끊김없이 처음부터 반복해서 재생된다.
     * 음악 길이보다 재생된 시간이 짧을 때는 처음부터 재생시간만큼만 재생된다.
     * 음악이 00:00를 넘겨서까지 재생되는 일은 없다.
     */
    /* 각 변수들 설명
     * musicinfos[i] -> "시작된 시각,끝난 시각,음악 제목, 악보"
     * m[i] 의 집합 -> 12개
     * a -> 1 <= m.length <= 1439
     * b -> 1 <= musicinfos.length <= 100
     * 1 <= 음악 제목 <= 64
     */
    /* 반환
     * 조건이 일치하는 음악이 여러개일 경우 -> 재생된 시간이 제일 긴 음악제목 반환
     * 조건이 일치하는 음악이 없을 경우 -> "(None)" 반환
     */
    public String solution(String m, String[] musicinfos) {
      checkScore = Map.ofEntries(
              Map.entry("C#", "1"),
              Map.entry("D#", "2"),
              Map.entry("F#", "3"),
              Map.entry("G#", "4"),
              Map.entry("A#", "5")
      );
      String target = convert(m);

      List<Music> musicList = new ArrayList<>();
      // O(b)
      for (String musicinfo : musicinfos) {
        String[] split = musicinfo.split(",");
        // 최악 -> playTime이 24시간 내내 -> 1440
        // O(1440b) -> 1440 * 100 -> 144,000
        musicList.add(new Music(LocalTime.parse(split[0]), LocalTime.parse(split[1]), split[2], convert(split[3])));
      }

      List<Music> candidate = new ArrayList<>();
      // O(b) -> 100
      for (Music music : musicList) {
        if (!music.score.contains(target)) continue;
        candidate.add(music);
      }

      // 최악 -> 100개
      int size = candidate.size();
      if (size == 0) return "(None)";
      else if (size == 1) return candidate.get(0).name;
      else {
        Music selected = candidate.get(0);
        // O(b) -> 100
        for (int i = 1; i < size; i++) {
          if (selected.playTime < candidate.get(i).playTime) selected = candidate.get(i);
        }
        return selected.name;
      }
    }


    // !!
    private String convert(String score) {
      // 5번 반복
      for (Map.Entry<String, String> entry : checkScore.entrySet()) {
        score = score.replaceAll(entry.getKey(), entry.getValue());
      }
      return score;
    }

    private static class Music {
      private final String name;
      private final String score;
      private final int playTime;

      public Music(LocalTime start, LocalTime end, String name, String score) {
        this.name = name;
        this.playTime = (int) start.until(end, ChronoUnit.MINUTES);
        this.score = score(playTime, score);
      }

      private String score(int playTime, String score) {
        int length = score.length();
        if (playTime <= length) return score.substring(0, playTime);
        StringBuilder sb = new StringBuilder();
        // 최대 -> 1440 [24시간 전부]
        for (int i = 0; i < playTime; i++) sb.append(score.charAt(i % length));
        return sb.toString();
      }
    }
  }
}
