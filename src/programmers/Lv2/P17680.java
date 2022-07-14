package programmers.Lv2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class P17680 {
  public static void main(String[] args) {
    int cacheSize = 2;
    String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};
    System.out.println(new Solution().solution(cacheSize, cities));
  }

  private static class Solution {

    /*
     * 0 <= cacheSize <= 30
     * cities.length <= 100,000
     */
    /*
    [조건]
    * 캐시 교체 알고리즘 -> LRU
    * cache hit -> 실행 시간 1
    * cache miss -> 실행시간 5
     */
    // answer -> 총 실행 시간
    public int solution(int cacheSize, String[] cities) {
      if (cacheSize == 0) return cities.length * 5;
      Map<String, Integer> cache = getLinkedHashMap(cacheSize);
      int answer = 0;
      for (String city : cities) {
        String key = city.toLowerCase();
        if (cache.containsKey(key)) answer += 1;
        else answer += 5;
        cache.put(key, 0);
      }
      ArrayList<Object> list = new ArrayList<>();
      return answer;
    }

    private LinkedHashMap<String, Integer> getLinkedHashMap(int cacheSize) {
      return new LinkedHashMap<>(cacheSize + 2, 1, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
          return this.size() == cacheSize + 1;
        }
      };
    }
  }
}
