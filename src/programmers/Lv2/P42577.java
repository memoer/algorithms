package programmers.Lv2;

import java.util.HashSet;
import java.util.Set;

public class P42577 {
  static class Solution {
    public boolean solution(String[] phoneBook) {
      Set<String> set = new HashSet<>(phoneBook.length);
      int min = Integer.MAX_VALUE;
      for (String s : phoneBook) {
        min = Math.min(min, s.length());
        set.add(s);
      }
      for (String s : phoneBook) {
        int length = s.length();
        if (min == length) {
          continue;
        }
        for (int i = min; i < length; i++) {
          if (set.contains(s.substring(0, i))) {
            return false;
          }
        }
      }
      return true;
    }
  }

  public static void main(String[] args) {
    String[] phoneBook = { "1", "2", "3", "9", "89", "49" };
    System.out.println(new Solution().solution(phoneBook));
  }

}
