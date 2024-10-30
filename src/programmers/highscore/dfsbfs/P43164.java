package programmers.highscore.dfsbfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P43164 {

    public static void main(String[] args) {
        String[][] tickets = {
            {"ICN", "JFK"},
            {"HND", "IAD"},
            {"JFK", "HND"},
        };
        String[] answer = new Solution().solution(tickets);

        for (String s : answer) {
            System.out.printf("%s ", s);
        }
        System.out.println();
    }

    private static class Solution {

        private final Map<String, List<String>> map = new HashMap<>();
        private final Map<String, boolean[]> visited = new HashMap<>();
        private boolean findAnswer = false;
        private String[] answer;
        private int cnt;

        public String[] solution(String[][] tickets) {
            initialize(tickets);
            String START = "ICN";
            Deque<String> dq = new ArrayDeque<>(List.of(START));
            dfs(START, dq, 0);
            return answer;
        }

        private void initialize(String[][] tickets) {
            this.cnt = tickets.length;
            for (String[] ticket : tickets) {
                String a = ticket[0];
                String b = ticket[1];
                if (!map.containsKey(a)) {
                    map.put(a, new ArrayList<>());
                }
                map.get(a).add(b);
            }
            for (String key : map.keySet()) {
                List<String> list = map.get(key);
                list.sort(String::compareTo);
                visited.put(key, new boolean[list.size()]);
            }
        }

        private void dfs(String cur, Deque<String> dq, int acc) {
            if (acc >= cnt) {
                this.findAnswer = true;
                this.answer = dq.toArray(new String[0]);
                return;
            }

            List<String> candidates = map.get(cur);
            if (candidates == null) {
                return;
            }

            int size = candidates.size();
            for (int i = 0; i < size && !findAnswer; i++) {
                if (visited.get(cur)[i]) {
                    continue;
                }
                visited.get(cur)[i] = true;
                dq.addLast(candidates.get(i));
                dfs(candidates.get(i), dq, acc + 1);
                dq.removeLast();
                visited.get(cur)[i] = false;
            }
        }
    }
}