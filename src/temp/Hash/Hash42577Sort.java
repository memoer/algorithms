package temp.Hash;

import java.util.Arrays;

public class Hash42577Sort {
  static class Solution {
    public boolean solution(String[] phoneBook) {
      Arrays.sort(phoneBook);
      for (String s : phoneBook) {
        System.out.println(s);
      }
      for (int i = 1; i < phoneBook.length; i++) {
        String pre = phoneBook[i - 1];
        String cur = phoneBook[i];
        if (cur.startsWith(pre)) {
          return false;
        }
      }
      return true;
    }
  }

  public static void main(String[] args) {
    String[] phoneBook = { "119", "120", "97674223", "1195524421" };
    System.out.println(new Solution().solution(phoneBook));
  }
}
