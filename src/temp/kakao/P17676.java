package temp.kakao;

import java.util.ArrayList;
import java.util.List;

class P17676 {
  public static void main(String[] args) {
    String[] lines = {
            "2016-09-15 01:00:04.002 2.0s",
            "2016-09-15 01:00:07.000 2s"
    };
    System.out.println(new P17676().solution(lines));
  }

  public int solution(String[] lines) {
    List<Request> logs = new ArrayList<>();
    for (String line : lines) {
      String[] split = line.split(" ");
      double seconds = Double.parseDouble(split[2].substring(0, split[2].length() - 1));

      String[] time = split[1].split(":");
      double end = 0;
      end += Double.parseDouble(time[0]) * 3600;
      end += Double.parseDouble(time[1]) * 60;
      end += Double.parseDouble(time[2]);

      logs.add(new Request(end - seconds + 0.001, end));
    }
    int ans = 1;
    int size = logs.size();
    for (int i = 0; i < size; i++) {
      double end = logs.get(i).end;
      int tmp = 1;
      for (int j = i + 1; j < size; j++) {
        double start = logs.get(j).start;
        if (start <= end || start < end + 1) tmp += 1;
      }
      ans = Integer.max(ans, tmp);
    }
    return ans;
  }

  private static class Request {
    private final double start;
    private final double end;

    public Request(double start, double end) {
      this.start = start;
      this.end = end;
    }
  }
}
