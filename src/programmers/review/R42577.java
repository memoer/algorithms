package programmers.review;

import java.util.HashMap;
import java.util.Map;

public class R42577 {
  static class Solution {
    public boolean solution(String[] phoneBook) {
      Map<String, Character> map = new HashMap<>();
      for (String phone : phoneBook) {
        map.put(phone, '_');
      }
      for (int i = 0; i < phoneBook.length; i++) {
        for (int j = 1; j < phoneBook[i].length(); j++) {
          String prefix = phoneBook[i].substring(0, j);
          if (map.containsKey(prefix)) {
            return false;
          }
        }
      }
      return true;
    }
  }

  public static void main(String[] args) {
    String[] phoneBook = { "12", "111", "123" };
    System.out.println(new Solution().solution(phoneBook));
  }
}
