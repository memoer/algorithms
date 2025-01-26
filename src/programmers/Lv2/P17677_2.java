package programmers.Lv2;

import java.util.HashMap;
import java.util.Map;

public class P17677_2 {

    public static void main(String[] args) {
        String str1 = "FRANCE";
        String str2 = "french";

        int solution = new Solution().solution(str1, str2);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(String str1, String str2) {
            Map<String, Integer> map1 = extract(str1.toLowerCase());
            Map<String, Integer> map2 = extract(str2.toLowerCase());
            if (map1.isEmpty() && map2.isEmpty()) {
                return 65_536;
            }

            int intersection = intersection(map1, map2);
            int union = union(map1, map2);
            return (int) Math.floor((double) intersection / union * 65_536);
        }

        private Map<String, Integer> extract(String str) {
            Map<String, Integer> map = new HashMap<>();
            int len = str.length() - 1;

            for (int i = 0; i < len; i++) {
                String sub = str.substring(i, i + 2);
                if (!sub.chars().allMatch(Character::isAlphabetic)) {
                    continue;
                }

                map.put(sub, map.getOrDefault(sub, 0) + 1);
            }

            return map;
        }

        private int intersection(Map<String, Integer> map1, Map<String, Integer> map2) {
            int count = 0;
            for (String key : map1.keySet()) {
                if (!map2.containsKey(key)) {
                    continue;
                }

                count += Integer.min(map1.get(key), map2.get(key));
            }
            return count;
        }

        private int union(Map<String, Integer> map1, Map<String, Integer> map2) {
            Map<String, Integer> temp = new HashMap<>(map1);
            for (String key : map2.keySet()) {
                int v = Integer.max(temp.getOrDefault(key, 0), map2.get(key));
                temp.put(key, v);
            }
            return temp.values().stream().mapToInt(v -> v).sum();
        }
    }
}
