package temp.kakao;

import java.util.LinkedHashMap;
import java.util.Map;

public class P17680LinkedHashMap {
  private final Object o = new Object();

  public int solution(int cacheSize, String[] cities) {
    int ans = 0;
    LinkedHashMap<String, Object> lru = getLRU(cacheSize);

    for (String city : cities) {
      String target = city.toLowerCase();
      if (lru.containsKey(target)) ans += 1;
      else ans += 5;
      lru.put(target, o); // refresh (or) add new str
    }
    return ans;
  }

  private LinkedHashMap<String, Object> getLRU(int cacheSize) {
    return new LinkedHashMap<>(cacheSize, 0.75f, true) {
      @Override
      protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
        return this.size() > cacheSize;
      }
    };
  }
}
