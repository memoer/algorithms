package temp.kakao;

import java.util.ArrayList;
import java.util.List;

/**
 * 음악 제목, 재생이 시작되고 끝난 시각, 악보를 제공
 * 네오가 기억한 멜로디와 악보에 사용되는 음은 "C, D, E, F, G, A, B, C#, D#, F#, G#, A#" 12개
 * 각 음은 "1분에 1개씩 재생"된다. 음악은 반드시 처음부터 재생되며 "음악 길이보다 재생된 시간이 길 때는 음악이 끊김없이 처음부터 반복해서 재생"된다.
 * - 음악 길이보다 재생된 시간이 짧을 때는 처음부터 재생 시간만큼만 재생된다.
 * 음악이 00:00를 넘겨서까지 재생되는 일은 없다.
 * 조건이 일치하는 음악이 여러 개일 때에는, 라디오에서 "재생된 시간이 제일 긴 음악 제목을 반환"한다. 재생된 시간이 같을 경우, "먼저 입력된 음악 제목을 반환"한다.
 * 조건이 일치하는 음악이 없을 때에는 “(None)”을 반환
 */
public class P17683 {
  public String solution(String m, String[] musicinfos) {
    String replacedM = edit(m);
    List<Music> musicList = getMusicList(replacedM, musicinfos);
    return getAns(musicList);
  }

  private String edit(String s) {
    StringBuilder sb = new StringBuilder(s);
    String[] src = new String[]{"C#", "D#", "F#", "G#", "A#"};
    String[] dist = new String[]{"1", "2", "3", "4", "5"};
    int idx = -1;
    for (int i = 0; i < 5; i++) while ((idx = sb.indexOf(src[i])) != -1) sb.replace(idx, idx + 2, dist[i]);
    return sb.toString();
  }

  private List<Music> getMusicList(String m, String[] musicinfos) {
    List<Music> musicList = new ArrayList<>();
    int len = musicinfos.length;
    for (int i = 0; i < len; i++) {
      String[] split = musicinfos[i].split(",");
      int playTimeOnMinutes = getPlayTime(split[0], split[1]);
      String music = editAndConcat(split[3], playTimeOnMinutes);
      if (music.contains(m)) musicList.add(new Music(i, split[2], playTimeOnMinutes));
    }
    return musicList;
  }

  private int getPlayTime(String a, String b) {
    String[] splitA = a.split(":");
    String[] splitB = b.split(":");
    int start = Integer.parseInt(splitA[0]) * 60 + Integer.parseInt(splitA[1]);
    int end = Integer.parseInt(splitB[0]) * 60 + Integer.parseInt(splitB[1]);
    return end - start;
  }

  private String concatMusic(String music, int playTime) {
    int len = music.length();
    if (playTime <= len) return music.substring(0, playTime);
    StringBuilder sb = new StringBuilder();
    int idx = 0;
    for (int i = 0; i < playTime; i++) {
      if (idx >= len) idx = 0;
      sb.append(music.charAt(idx++));
    }
    return sb.toString();
  }

  private String editAndConcat(String music, int playTime) {
    String replace = edit(music);
    return concatMusic(replace, playTime);
  }

  private String getAns(List<Music> list) {
    int size = list.size();
    if (size == 0) return "(None)";
    else if (size == 1) return list.get(0).name;
    else {
      list.sort((pre, cur) -> {
        int compare = Integer.compare(cur.minutes, pre.minutes);
        return compare != 0 ? compare : Integer.compare(pre.idx, cur.idx);
      });
      return list.get(0).name;
    }
  }

  private static class Music {
    int idx;
    String name;
    int minutes;

    public Music(int idx, String name, int minutes) {
      this.idx = idx;
      this.name = name;
      this.minutes = minutes;
    }
  }
}
