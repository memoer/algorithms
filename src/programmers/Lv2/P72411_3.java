package programmers.Lv2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class P72411_3 {

    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] courses = {2, 3, 4};
        String[] solution = new Solution().solution(orders, courses);
        for (String s : solution) {
            System.out.println(s);
        }
    }

    private static class Solution {

        private final Map<String, Integer> map = new HashMap<>();
        private int[] max;

        public String[] solution(String[] orders, int[] courses) {
            max = new int[11];
            for (String order : orders) {
                int len = order.length();
                String sorted = sortedOrder(order);
                for (int course : courses) {
                    combination(len, course, 0, sorted, new StringBuilder());
                }
            }
            return map.entrySet().stream()
                .filter(e -> e.getValue() >= 2)
                .filter(e -> {
                    int len = e.getKey().length();
                    return e.getValue() == max[len];
                })
                .map(Entry::getKey)
                .sorted(String::compareTo)
                .toArray(String[]::new);
        }

        private void combination(int n, int r, int start, String target, StringBuilder sb) {
            if (r == sb.length()) {
                String candidate = sb.toString();
                int cnt = map.getOrDefault(candidate, 0) + 1;
                map.put(candidate, cnt);
                max[r] = Math.max(max[r], cnt);
                return;
            }

            for (int i = start; i < n; i++) {
                sb.append(target.charAt(i));
                combination(n, r, i + 1, target, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        private String sortedOrder(String order) {
            char[] chars = order.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }

    }
}
