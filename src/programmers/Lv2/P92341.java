package programmers.Lv2;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class P92341 {
  public static void main(String[] args) {
    int[] fees = {1, 461, 1, 10};
    String[] records = {"00:00 1234 IN"};
    for (int num : new Solution().solution(fees, records)) System.out.print(num + ", ");
  }

  private static class Solution {

    /*
    1. 어떤 차량이 입차된 후, 출차 내역이 없으면 23:59에 출차된 것으로 간주
    2. "00:00 ~ 23:59" 까지의 입/출차 내역을 바탕으로 차량별 누적 주차 시간을 계산하여 요금을 일괄로 계산
    3. 누적 주차 시간이 "기본 시간"이하라면, "기본 요금"을 청구
    4. 누적 주차 시간이 "기본 시간"을 초과하면, "기본 요금"에 더해 초과한 시간에 대해 "단위 시간"마다 "단위 요금"을 청구
        - 초과한 시간이 "단위 시간"으로 나누어 떨어지지 않으면, "올림"합니다
     */
    /*
     * fee -> 주차 요금을 나타내는 정수 배열
          - fee[0] -> 기본 시간[분]
          - fee[1] -> 기본 요금[원]
          - fee[2] -> 단위 시간[분]
          - fee[3] -> 단위 요금[원]
     * records -> 자동차의 입/출차 내역
     * answer -> 차량 번호가 작은 자동차부터, 청구할 주차 요금을 차례대로 담은 정수 배열
     */
    public int[] solution(int[] fees, String[] records) {
      Map<String, Car> history = getHistory(records);
      for (Car car : history.values()) {
        car.addFee(fees[1]);
        if (car.spentTime > fees[0]) {
          int excessUnitTime = getExcessUnitTime((int) (car.spentTime - fees[0]), fees[2]);
          car.addFee(excessUnitTime * fees[3]);
        }
      }
      return history.entrySet().stream().sorted(Map.Entry.comparingByKey()).mapToInt(e -> e.getValue().fee).toArray();
    }

    private int getExcessUnitTime(int excessTime, int unit) {
      return excessTime / unit + (excessTime % unit == 0 ? 0 : 1);
    }

    private Map<String, Car> getHistory(String[] records) {
      Map<String, LocalTime> temp = new HashMap<>();
      Map<String, Car> carMap = new HashMap<>();
      for (String record : records) {
        String[] split = record.split(" ");
        LocalTime time = LocalTime.parse(split[0]);
        String number = split[1];
        switch (split[2]) {
          case "IN" -> temp.put(number, time);
          case "OUT" -> {
            long spentTime = temp.get(number).until(time, ChronoUnit.MINUTES);
            putCar(carMap, number, spentTime);
            temp.remove(number);
          }
          default -> throw new UnsupportedOperationException();
        }
      }
      LocalTime end = LocalTime.parse("23:59");
      for (String number : temp.keySet()) {
        long spentTime = temp.get(number).until(end, ChronoUnit.MINUTES);
        putCar(carMap, number, spentTime);
      }
      return carMap;
    }

    private void putCar(Map<String, Car> carMap, String number, long spentTime) {
      if (!carMap.containsKey(number)) carMap.put(number, new Car(spentTime));
      else carMap.get(number).addSpentTime(spentTime);
    }

    private static class Car{
      private long spentTime;
      private int fee;

      public Car(long spentTime) {
        this.spentTime = spentTime;
      }

      public void addSpentTime(long spentTime) {
        this.spentTime += spentTime;
      }

      public void addFee(int fee) {
        this.fee += fee;
      }
    }
  }
}
