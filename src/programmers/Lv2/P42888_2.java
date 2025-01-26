package programmers.Lv2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class P42888_2 {

    public static void main(String[] args) {
        String[] record = {
            "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"
        };
        String[] solution = new Solution().solution(record);

        for (String s : solution) {
            System.out.println(s);
        }
    }

    private static class Solution {

        public String[] solution(String[] record) {
            final Map<String, String> name = new HashMap<>();
            final List<String> history = new ArrayList<>();

            for (String s : record) {
                String[] split = s.split(" ");
                switch (split[0]) {
                    case "Enter" -> {
                        name.put(split[1], split[2]);
                        String format = String.format("! %s", split[1]);
                        history.add(format);
                    }
                    case "Leave" -> {
                        String format = String.format("@ %s", split[1]);
                        history.add(format);
                    }
                    case "Change" -> name.put(split[1], split[2]);
                }
            }

            return history.stream().map(v -> {
                String[] split = v.split(" ");
                String s = Objects.equals(split[0], "!") ? "님이 들어왔습니다." : "님이 나갔습니다.";
                return String.format("%s%s", name.get(split[1]), s);
            }).toArray(String[]::new);
        }

    }
}
