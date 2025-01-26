package programmers.Lv2;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class P17680_2 {

    public static void main(String[] args) {
        int size = 3;
        String[] cities = {
            "Jeju", "Pangyo", "Seoul"
        };
        int solution = new Solution().solution(size, cities);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int size, String[] cities) {
//            `accessOrder`를 `true`로 설정해야, LRU 정책이 반영된다.
            LinkedHashMap<String, Object> map = new LinkedHashMap<>(size, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Entry<String, Object> eldest) {
                    return this.size() > size;
                }
            };

            Object o = new Object();
            int answer = 0;
            for (String city : cities) {
                String str = city.toLowerCase();
                int latency = map.containsKey(str) ? 1 : 5;
                answer += latency;
                map.put(str, o);
            }

            return answer;
        }

    }
}
