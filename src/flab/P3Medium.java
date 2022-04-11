package flab;

public class P3Medium {
  static class Solution {
    private final String OPEN = "<br>";
    private final String CLOSE = "</br>";

    public String solution(String s, String[] dict) {
      StringBuilder answer = new StringBuilder();
      StringBuilder sb = new StringBuilder(s);
      while (sb.length() != 0) {
        int start = -1;
        int end = -1;
        for (String d : dict) {
          int idx = sb.indexOf(d);
          if (idx == -1) {
            continue;
          }
          if (start == -1 && end == -1) {
            start = idx;
            end = idx + d.length();
          } else if (idx <= end + 1) {
            start = Math.min(idx, start);
            end = Math.max(idx + d.length(), end);
          } else {
            break;
          }
        }
        if (start == -1 && end == -1) {
          answer.append(sb.substring(0));
          sb.delete(0, sb.length());
          continue;
        }
        if (start > 0) {
          for (int i = 0; i < start; i++) {
            answer.append(sb.charAt(i));
          }
        }
        answer.append(wrap(sb.subSequence(start, end)));
        sb.delete(0, end);
      }
      return answer.toString();
    }

    private String wrap(CharSequence chs) {
      StringBuilder sb = new StringBuilder(chs);
      sb.insert(0, this.OPEN);
      sb.append(this.CLOSE);
      return sb.toString();
    }
  }

  public static void main(String[] args) {
    // String s = "aaabbccaabc";
    // String[] dict = { "aab", "aaa", "bc" };
    // System.out.println(new Solution().solution(s, dict));

    // String s = "abcxyz123";
    // String[] dict = { "abc", "123" };
    // System.out.println(new Solution().solution(s, dict));

    // String s = "abcxyz123";
    // String[] dict = { "abc", "xyz" };
    // System.out.println(new Solution().solution(s, dict));

    String s = "aaabbcc";
    String[] dict = { "aaa", "aab", "bc" };
    System.out.println(new Solution().solution(s, dict));
  }

}
