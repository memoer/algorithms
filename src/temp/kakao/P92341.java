package temp.kakao;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class P92341 {
  /**
   * 입차 후, 출차된 내용 없다면 23:59로 출차된 것으로 간주
   * 누적 주차 시간이
   * - "기본 시간" 이하라면, "기본 요금" 청구
   * - "기본 시간"을 초과하면, "기본 요금 + 단위시간 * 단위요금" 청구 [초과시간이 "단위 시간"으로 나누어 떨어지지 않으면, "올림"]
   */
  // fee[0] -> 기본 시간[분], fee[1] -> 기본 요금[원], fee[2] -> 단위 시간[분], fee[3] -> 단위 요금[원]
  // 차량 번호가 작은 자동차부터 주차 요금 배열을 return
  private int[] fees;

  public static void main(String[] args) {
    int[] fees = {180, 5000, 10, 600};
    String[] records = {
            "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN",
            "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"
    };
    for (int n : new P92341().solution(fees, records)) System.out.println(n);
  }

  public int[] solution(int[] fees, String[] records) {
    this.fees = fees;
    return getMap(records).entrySet().stream()
            .map(this::createCar)
            .sorted(Comparator.comparingInt(pre -> pre.num))
            .mapToInt(Car::getFee)
            .toArray();
  }

  private Map<Integer, Deque<Staytime>> getMap(String[] records) {
    Map<Integer, Deque<Staytime>> map = new HashMap<>();
    for (String record : records) {
      String[] split = record.split(" ");
      String time = split[0];
      Integer car = Integer.valueOf(split[1]);
      String type = split[2];
      switch (type) {
        case "IN" -> {
          if (!map.containsKey(car)) map.put(car, new ArrayDeque<>());
          map.get(car).addFirst(new Staytime(time));
        }
        case "OUT" -> map.get(car).peekFirst().setOut(time);
        default -> throw new UnsupportedOperationException();
      }
    }
    return map;
  }

  private Car createCar(Map.Entry<Integer, Deque<Staytime>> entry) {
    int usageTime = 0;
    Deque<Staytime> dq = entry.getValue();
    while (!dq.isEmpty()) {
      Staytime staytime = dq.pollFirst();
      LocalTime inTime = LocalTime.parse(staytime.getIn());
      LocalTime outTime = LocalTime.parse(staytime.getOut() == null ? "23:59" : staytime.getOut());
      usageTime += inTime.until(outTime, ChronoUnit.MINUTES);
    }
    return new Car(entry.getKey(), getUsageFee(usageTime));
  }

  private int getUsageFee(int usageTime) {
    int fee = fees[1];
    return fee + (usageTime <= fees[0] ? 0 : getUsageUnit(usageTime));
  }

  private int getUsageUnit(int until) {
    return (int) Math.ceil((double) (until - fees[0]) / fees[2]) * fees[3];
  }

  private static class Car {
    private int num;
    private int fee;

    public Car(int num, int fee) {
      this.num = num;
      this.fee = fee;
    }

    public int getNum() {
      return num;
    }

    public void setNum(int num) {
      this.num = num;
    }

    public int getFee() {
      return fee;
    }

    public void setFee(int fee) {
      this.fee = fee;
    }
  }

  private static class Staytime {
    private String in;
    private String out;

    public Staytime(String in) {
      this.in = in;
    }

    public String getIn() {
      return in;
    }

    public void setIn(String in) {
      this.in = in;
    }

    public String getOut() {
      return out;
    }

    public void setOut(String out) {
      this.out = out;
    }
  }
}
