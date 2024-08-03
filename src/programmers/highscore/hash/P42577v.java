package programmers.highscore.hash;

import java.util.Arrays;

public class P42577v {

  public static void main(String[] args) {

  }

  public static class Solution {

    public boolean solution(String[] phoneBook) {
      Arrays.sort(phoneBook);
      int limit = phoneBook.length - 1;

      for (int i = 0; i < limit; i++) {
        String a = phoneBook[i];
        String b = phoneBook[i + 1];
        if (b.startsWith(a)) {
          return false;
        }
      }
      return true;
    }
  }
}
