package programmers.Lv1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P150370 {

    public static void main(String[] args) {
        String today = "2009.12.28";
        String[] terms = {"A 13"};
        String[] privacies = {"2008.11.03 A"};
        int[] solution = new Solution().solution(today, terms, privacies);
        for (int i : solution) {
            System.out.printf("%d ", i);
        }
        System.out.println();
    }

    private static class Solution {

        public int[] solution(String today, String[] terms, String[] privacies) {
            Map<Character, Integer> map = new HashMap<>();
            for (String term : terms) {
                String[] split = term.split(" ");
                map.put(split[0].charAt(0), Integer.valueOf(split[1]));
            }

            List<Integer> answer = new ArrayList<>();
            int len = privacies.length;
            for (int i = 0; i < len; i++) {
                String[] split = privacies[i].split(" ");
                String createdAt = split[0];
                Integer plusMonth = map.get(split[1].charAt(0));
                String deleteDateTime = calculate(createdAt, plusMonth);
                System.out.printf("%s, %d, %s\n", createdAt, plusMonth, deleteDateTime);
                if (today.compareTo(deleteDateTime) > 0) {
                    answer.add(i + 1);
                }
            }
            return answer.stream().mapToInt(i -> i).toArray();
        }

        private String calculate(String createdAt, int plusMonth) {
            String[] date = createdAt.split("\\.");
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);

            year += plusMonth / 12;
            month += plusMonth % 12;
            if (month > 12) {
                year += 1;
                month = month % 12;
            }
            if (day != 1) {
                day -= 1;
            } else {
                day = 28;
                if (month == 1) {
                    month = 12;
                    year -= 1;
                } else {
                    month -= 1;
                }
            }

            return String.format("%d.%02d.%02d", year, month, day);
        }
    }
}
