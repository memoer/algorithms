package programmers.review;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class P72411 {

    public static void main(String[] args) {
        String[] orders = {
            "XYZ", "XWY", "WXA"
        };
        int[] courses = {2, 3, 4};
        String[] solution = new Solution().solution(orders, courses);

        for (String s : solution) {
            System.out.print(s + ", ");
        }
        System.out.println();
    }

    private static class Solution {

        Map<String, Integer> map = new HashMap<>();
        private int[] max;

        public String[] solution(String[] orders, int[] courses) {
            max = new int[11];
            for (int course : courses) {
                for (String order : orders) {
                    char[] chars = order.toCharArray();
                    Arrays.sort(chars);
                    String s = new String(chars);

                    dfs(course, 0, new StringBuilder(), s);
                }
            }

            return map.entrySet().stream()
                .filter(e -> e.getValue() >= 2 && max[e.getKey().length()] == e.getValue())
                .map(Entry::getKey)
                .sorted(String::compareTo)
                .toArray(String[]::new);
        }


        private void dfs(int r, int start, StringBuilder sb, String target) {
            if (r == sb.length()) {
                String s = sb.toString();
                map.put(s, map.getOrDefault(s, 0) + 1);
                max[s.length()] = Integer.max(max[s.length()], map.get(s));
                return;
            }

            for (int i = start; i < target.length(); i++) {
                sb.append(target.charAt(i));
                dfs(r, i + 1, sb, target);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

    }
}
