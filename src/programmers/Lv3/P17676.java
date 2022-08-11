package programmers.Lv3;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P17676 {
  public static void main(String[] args) {
    String[] lines = {
            "2016-09-15 01:00:04.002 2.0s",
            "2016-09-15 01:00:07.000 2s"
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
      int answer = 0;
      List<LocalTime[]> logs = Arrays.stream(lines)
              .map(s -> {
                String[] split = s.split(" ");
                LocalTime end = LocalTime.parse(split[1]);
                LocalTime start = end
                        .plusNanos(1_000_000)
                        .minus(Duration.parse("PT" + split[2]));
                return new LocalTime[]{start, end};
              })
              .collect(Collectors.toList());
      int size = logs.size();
      for (int i = 0; i < size - 1; i++) {
        int throughput = 0;
        LocalTime aEnd = logs.get(i)[1];
        for (int j = i + 1; j < size; j++) {
          LocalTime bStart = logs.get(j)[0];
          if (bStart.isBefore(aEnd)) {
            LocalTime bEnd = logs.get(j)[1];
            if (bEnd.isAfter(aEnd) || bEnd.equals(aEnd)) throughput += 1;
          } else if (ChronoUnit.SECONDS.between(aEnd, bStart) < 1) throughput += 1;
          else break;
        }
        answer = Math.max(answer, throughput);
      }
      return answer + 1;
    }
  }
}
