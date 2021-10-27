package programmers.hash;

import java.util.HashMap;

public class H42577 {
  public static void main(String[] args) throws Exception {
    String[] phoneBook = { "97674223", "119", "1195524421" };
    solution(phoneBook);
  }

  public static boolean solution(String[] phoneBook) throws Exception {
    boolean answer = true;
    HashMap<String, Boolean> map = new HashMap<>();
    int minLength = Integer.MAX_VALUE;
    for (String phoneNumber : phoneBook) {
      int phoneNumberLength = phoneNumber.length();
      if (phoneNumberLength < minLength) {
        minLength = phoneNumberLength;
      }
      map.put(phoneNumber, true);
    }
    for (String phoneNumber : phoneBook) {
      if (phoneNumber.length() == minLength) {
        continue;
      } else if (phoneNumber.length() < minLength) {
        throw new Exception("Error!");
      }
      for (int i = minLength; i < phoneNumber.length(); i++) {
        String check = phoneNumber.substring(0, i);
        if (map.containsKey(check)) {
          answer = false;
          break;
        }
      }
    }
    return answer;
  }
}
