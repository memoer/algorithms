package temp.kakao;

public class P17680 {
  private int size = 0;

  public static void main(String[] args) {
    int size = 2;
    String[] cities = new String[]{"Jeju", "Pangyo", "NewYork", "newyork"};
    System.out.println(new P17680().solution(size, cities));
  }

  public int solution(int cacheSize, String[] cities) {
    if (cacheSize == 0) return cities.length * 5;
    int ans = 0;
    String[] cache = new String[cacheSize];
    for (String city : cities) {
      String target = city.toLowerCase();
      int idx = search(cache, target);
      if (idx != -1) {
        ans += 1;
        refreshOnHit(cache, idx);
      } else {
        ans += 5;
        refreshOnMiss(cache, target, cacheSize);
      }
    }
    return ans;
  }

  private int search(String[] cache, String city) {
    if (size == 0) return -1;
    int idx = -1;
    for (int i = 0; i < size; i++) if (cache[i].equals(city)) return i;
    return idx;
  }

  private void refreshOnHit(String[] cache, int idx) {
    String target = cache[idx];
    int lastIdx = size - 1;
    for (int i = idx; i < lastIdx; i++) cache[i] = cache[i + 1];
    cache[lastIdx] = target;
  }

  private void refreshOnMiss(String[] cache, String city, int cacheSize) {
    if (size == 0 || size != cacheSize) {
      cache[size++] = city;
    } else {
      int lastIdx = size - 1;
      for (int i = 0; i < lastIdx; i++) cache[i] = cache[i + 1];
      cache[lastIdx] = city;
    }
  }
}
