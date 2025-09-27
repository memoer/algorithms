package programmers.Lv2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class P92341_2 {

    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN",
            "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        int[] solution = new Solution().solution(fees, records);
        for (int i : solution) {
            System.out.println(i + ", ");
        }
        System.out.println();
    }

    private static class Solution {

        public int[] solution(int[] fees, String[] records) {
            Map<String, Car> map = new HashMap<>();

            for (String record : records) {
                String[] split = record.split(" ");
                String time = split[0];
                String num = split[1];
                String type = split[2];
                switch (type) {
                    case "IN" -> {
                        if (map.containsKey(num)) {
                            Car car = map.get(num);
                            car.in(time);
                        } else {
                            Car car = new Car(num);
                            car.in(time);
                            map.put(num, car);
                        }
                    }
                    case "OUT" -> map.get(num).out(time);
                }
            }
            for (Car car : map.values()) {
                car.out();
                car.setPrice(fees);
            }
            return map.values().stream().sorted(Comparator.comparing(v -> v.num)).mapToInt(v -> v.price).toArray();
        }


        private static class Car {

            boolean isOuted;
            String in;
            String out;
            String num;
            int totalDiff;
            int price;

            public Car(String num) {
                this.num = num;
            }

            public void in(String in) {
                this.in = in;
                this.out = "23:59";
                this.isOuted = false;
            }

            public void out(String out) {
                this.out = out;
                this.totalDiff += toMinutes(out) - toMinutes(in);
                this.isOuted = true;
            }

            public void out() {
                if (isOuted) {
                    return;
                }
                this.totalDiff += toMinutes(out) - toMinutes(in);
                this.isOuted = true;
            }

            public void setPrice(int[] fees) {
                if (this.totalDiff <= fees[0]) {
                    this.price = fees[1];
                } else {
                    this.price = (int) (fees[1] + Math.ceil((double) (this.totalDiff - fees[0]) / fees[2]) * fees[3]);
                }
            }

            private int toMinutes(String time) {
                String[] split = time.split(":");
                return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
            }
        }
    }
}
