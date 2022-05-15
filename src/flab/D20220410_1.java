package flab;

public class D20220410_1 {
  public static void main(String[] args) {
    String[] strs = {"dog", "racecar", "car"};
    System.out.println(new Solution().longestCommonPrefix(strs));
  }

  static class Solution {
    public String longestCommonPrefix(String[] strs) {
      StringBuilder sb = new StringBuilder("");
      int idx = 0;

      while (true) {
        char pre = '0';
        for (String str : strs) {
          if (idx >= str.length()) return sb.toString();
          char ch = str.charAt(idx);
          if (pre == '0') pre = ch;
          else if (pre != ch) return sb.toString();
        }
        idx += 1;
        sb.append(pre);
      }
    }
  }
}
