package programmers.Lv2;

public class P14683_2 {
  public static void main(String[] args) {
    String m = "CC#BCC#BCC#BCC#B";
    String[] musicinfos = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
    System.out.println(new Solution().solution(m, musicinfos));
  }

  private static class Solution {
    public String solution(String m, String[] musicinfos) {
      String answer = "(None)";
      int time = 0;
      m = convert(m);

      for (String musicinfo : musicinfos) {
        String[] info = musicinfo.split(",");
        int t = getMinutesDiff(info);
        if (t <= time) continue;
        String score = getScore(convert(info[3]), t);
        if (score.contains(m)) {
          answer = info[2];
          time = t;
        }
      }

      return answer;
    }

    private String getScore(String info, int t) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < t; i++) sb.append(info.charAt(i % info.length()));
      return sb.toString();
    }

    private int getMinutesDiff(String[] info) {
      int start = (60 * Integer.parseInt(info[0].substring(0, 2))) + Integer.parseInt(info[0].substring(3));
      int end = (60 * Integer.parseInt(info[1].substring(0, 2))) + Integer.parseInt(info[1].substring(3));
      int t = end - start;
      return t;
    }

    private String convert(String s) {
      s = s.replaceAll("C#", "1");
      s = s.replaceAll("D#", "2");
      s = s.replaceAll("F#", "3");
      s = s.replaceAll("G#", "4");
      s = s.replaceAll("A#", "5");
      return s;
    }
  }
}
