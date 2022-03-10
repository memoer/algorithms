package programmers.Lv2;

public class P60057Other {
  static class Solution {
    public int solution(String s) {
      int answer = Integer.MAX_VALUE;
      int len = s.length();
      int last = len / 2 + 1;

      for (int size = 1; size <= last; size++) {
        String pre = "";
        int cnt = 1;
        int sum = 0;
        for (int start = 0; start < len; start += size) {
          int end = start + size;
          String cur = s.substring(start, end > len ? len : end);
          if (cur.equals(pre)) {
            cnt += 1;
          } else {
            if (cnt != 1) {
              sum += Math.log10(cnt) + 1;
            }
            sum += pre.length();
            cnt = 1;
            pre = cur;
          }
        }
        sum += pre.length();
        if (cnt != 1) {
          sum += Math.log10(cnt) + 1;
        }
        answer = Math.min(answer, sum);
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    String[] sArr = { "aabbaccc", "ababcdcdababcdcd", "abcabcdede", "abcabcabcabcdededededede", "xababcdcdababcdcd" };
    System.out.println(new Solution().solution("abcabcabcabcdededededede"));
    // for (String s : sArr) {
    // System.out.println(new Solution().solution(s));
    // }
  }
}
