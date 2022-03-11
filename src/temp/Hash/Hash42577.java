package temp.Hash;

import java.util.HashMap;
import java.util.Map;

public class Hash42577 {
  static class Solution {
    public boolean solution(String[] phoneBook) {
      boolean answer = true;
      Map<String, Boolean> map = new HashMap<>();
      for (String phone : phoneBook) {
        map.put(phone, true);
      }
      for (String phone : phoneBook) {
        for (int i = 1; i < phone.length(); i++) {
          String s = phone.substring(0, i);
          if (map.containsKey(s)) {
            return false;
          }
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    String[] phoneBook = { "119", "97674223", "1195524421" };
    System.out.println(new Solution().solution(phoneBook));
  }
}
