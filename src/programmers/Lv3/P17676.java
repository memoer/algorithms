package programmers.Lv3;

import java.util.ArrayList;
import java.util.List;

public class P17676 {
  public static void main(String[] args) {
    String[] lines = {
            "2016-09-15 00:00:00.000 3s"
    };
    System.out.println(new Solution().solution(lines));
  }

  private static class Solution {
    // 초당 최대 처리량 -> 응답 완료 여부 관계없이 임의 시간부터 1초간 처리하는 요청의 최대 개수
    /*
    1. lines[i] -> 응답완료시간 S와 처리시간 T가 공백으로 구분되어 있음
    2. lines 배열은 응답완료시간 S를 기준으로 오름차순 정렬
    3. 서버 타임아웃은 3초로 적용 -> 처리 시간은 0.001 <= T <= 3.000
     */
    // return -> 로그 데이터 lines 배열에 대해 초당 최대 처리량 리턴
    public int solution(String[] lines) {
      List<Time> logs = new ArrayList<>();
      for (String line : lines) {
        String[] split = line.split(" ");
        double seconds = Double.parseDouble(split[2].substring(0, split[2].length() - 1));
        String[] time = split[1].split(":");

        double end = 0;
        end += Double.parseDouble(time[0]) * 3600;
        end += Double.parseDouble(time[1]) * 60;
        end += Double.parseDouble(time[2]);

        logs.add(new Time(end - seconds + 0.001, end));
      }


      int answer = 1;
      int size = logs.size();
      for (int i = 0; i < size - 1; i++) {
        int count = 1;
        double aEnd = logs.get(i).end + 1;
        for (int j = i + 1; j < size; j++) {
          double bStart = logs.get(j).start;
          if (aEnd > bStart) count += 1;
        }
        answer = Math.max(answer, count);
      }
      return answer;
    }

    private static class Time {
      private final double start;
      private final double end;

      public Time(double start, double end) {
        this.start = start;
        this.end = end;
      }
    }
  }
}
