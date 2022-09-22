package programmers.Lv3;

import java.util.*;

public class P17678 {
  public static void main(String[] args) {
    int n = 10, t = 60, m = 45;
    String[] timetable = new String[]{
            "23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"
    };
    System.out.println(new P17678().solution(n, t, m, timetable));
  }
  /**
   * n -> 셔틀 운행 횟수 [0 < n <= 10]
   * t -> 셔틀 운행 간격(분 단위) [0 < t <= 60]
   * m -> 한 셔틀에 탈 수 있는 최대 크루 수 [0 < m <= 45]
   * timetable -> 크루가 대기열에 도착하는 시각 배열 [0 < timetable.length <= 2,000]
   */
  /**
   * 1. 셔틀은 09:00에 처음 도착한다. 그 이후로 n회 t분 간격으로 도착한다.
   * 2. 셔틀은 최대 m명의 승객이 탈 수 있다.
   * 3. 셔틀이 도착했을 때, 대기 순서대로 m명 만큼 태운다.
   * 4. 콘은 가장 마지막으로 셔틀을 타고 싶어 한다. 이때, 가장 마지막으로 셔틀을 타기 위해 어느 시각에 대기해야 하는 지를 리턴하시오.
   * - 주의. 콘은 같은 시각에 도착한 크루 중, 대기열에서 제일 뒤에 선다.
   * - 주의. 모든 크루는 23:59에 집에 돌아간다. 어떤 크루도 다음날 셔틀을 타는 일은 없다.
   */

  public String solution(int n, int t, int m, String[] timetable) {
    int ans = 0;
    Map<Integer, Deque<Integer>> map = new HashMap<>();
    int len = timetable.length;
    int[] sortedTimeTable = Arrays.stream(timetable).mapToInt(this::convertToMinutes).sorted().toArray();

    for (int round = 0, i = 0, shuttleTime = 540; round < n; round++) {
      int rest = m;

      if (round != 0) {
        int tmp = shuttleTime + t;
        if (tmp < 1_440) shuttleTime = tmp;
        else {
          shuttleTime = tmp - 1_440;
          i = len;
        }
      }

      while (i < len && rest > 0 && sortedTimeTable[i] <= shuttleTime) {
        if (!map.containsKey(shuttleTime)) map.put(shuttleTime, new ArrayDeque<>());
        map.get(shuttleTime).addFirst(sortedTimeTable[i]);
        rest -= 1;
        i += 1;
      }

      if (rest == 0) ans = map.get(shuttleTime).peekFirst() - 1;
      else if (!map.containsKey(shuttleTime)) ans = shuttleTime;
      else ans = Math.max(map.get(shuttleTime).peekFirst(), shuttleTime);
    }
    return convertToString(ans);
  }

  private int convertToMinutes(String s) {
    String[] split = s.split(":");
    return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
  }

  private String convertToString(int v) {
    StringBuilder h = new StringBuilder(String.valueOf(v / 60));
    StringBuilder m = new StringBuilder(String.valueOf(v % 60));
    if (h.length() == 1) h.insert(0, "0");
    if (m.length() == 1) m.insert(0, "0");
    return h.append(":").append(m).toString();
  }
}
