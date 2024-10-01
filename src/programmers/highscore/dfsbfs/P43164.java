package programmers.highscore.dfsbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class P43164 {

    public static void main(String[] args) {
        String[][] ticketList = {
            {"ICN", "JFK"},
            {"HND", "IAD"},
            {"JFK", "HND"}
        };
        String[] solution = new Solution().solution(ticketList);
        for (String s : solution) {
            System.out.printf("%s \n", s);
        }
    }

    private static class Solution {

        public String[] solution(String[][] ticketList) {
            List<String[]> sortedList = Arrays.stream(Arrays.copyOf(ticketList, 1))
                .sorted(Comparator.comparing(o -> o[1]))
                .toList();

            List<String> answer = new ArrayList<>();
            boolean[] used = new boolean[ticketList.length];

            Queue<String[]> q = new LinkedList<>();
            q.offer(ticketList[0]);
            used[0] = true;
            while (!q.isEmpty()) {
                String[] cur = q.poll();
                answer.add(cur[0]);
                for (int i = 1; i < sortedList.size(); i++) {
                    String[] next = sortedList.get(i);
                    if (used[i] || !Objects.equals(cur[1], next[0])) {
                        continue;
                    }
                    q.offer(next);
                    used[i] = true;
                }
            }

            return answer.toArray(new String[0]);
        }

    }
}
