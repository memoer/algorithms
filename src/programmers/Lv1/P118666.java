package programmers.Lv1;

import java.util.HashMap;
import java.util.Map;

public class P118666 {

    public static void main(String[] args) {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};
        String solution = new Solution().solution(survey, choices);
        System.out.printf(solution);
    }

    private static class Solution {

        private final static Map<Character, Integer> ORDER = Map.ofEntries(
            Map.entry('R', 0),
            Map.entry('T', 0),
            Map.entry('C', 1),
            Map.entry('F', 1),
            Map.entry('J', 2),
            Map.entry('M', 2),
            Map.entry('A', 3),
            Map.entry('N', 3)
        );
        private final Map<Character, Integer> map = new HashMap<>();

        public Solution() {
            for (Character key : ORDER.keySet()) {
                map.put(key, 0);
            }
        }

        public String solution(String[] survey, int[] choices) {
            int len = survey.length;

            for (int i = 0; i < len; i++) {
                String[] split = survey[i].split("");
                char disagree = split[0].charAt(0);
                char agree = split[1].charAt(0);

                int choice = choices[i];
                if (choice <= 3) {
                    int score = choice == 1 ? 3 : choice == 2 ? 2 : 1;
                    map.put(disagree, map.get(disagree) + score);
                } else if (choice == 4) {
                } else {
                    int score = choice == 5 ? 1 : choice == 6 ? 2 : 3;
                    map.put(agree, map.get(agree) + score);
                }
            }

            char[] answer = new char[4];
            ORDER.entrySet()
                .stream()
                .sorted((e1, e2) -> {
                        int o1 = e1.getValue().compareTo(e2.getValue());
                        if (o1 != 0) {
                            return o1;
                        }
                        return e1.getKey().compareTo(e2.getKey());
                    }
                )
                .forEach(e -> {
                    Integer idx = e.getValue();
                    char v = answer[idx];
                    if (v == '\0') {
                        answer[idx] = e.getKey();
                    } else if (map.get(e.getKey()) > map.get(v)) {
                        answer[idx] = e.getKey();
                    }
                });
            return new String(answer);
        }

    }
}
