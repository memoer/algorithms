package programmers.Lv2;

public class P12951 {
  public static void main(String[] args) {
    String s = "3people unFollowed me ";
    System.out.println(new Solution().solution(s) + "/");
  }

  private static class Solution {
    public String solution(String s) {
      int length = s.length();
      char[] chars = s.toCharArray();
      boolean isFirst = true;
      for (int i = 0; i < length; i++) {
        char ch = chars[i];
        if (ch == ' ') isFirst = true;
        else if (Character.isAlphabetic(ch)) {
          if (isFirst) {
            chars[i] = Character.toUpperCase(ch);
            isFirst = false;
          } else chars[i] = Character.toLowerCase(ch);
        } else if (isFirst) isFirst = false;
      }
      return String.valueOf(chars);
    }
  }
}
