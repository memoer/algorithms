package flab;

public class P3Easy {
  static class Solution {
    public String solution(String[] strs) {
      final int length = strs.length;
      StringBuilder answer = new StringBuilder();
      int idx = 0;
      while (true) {
        boolean isStop = false;
        char ch = strs[0].charAt(idx);
        for (int i = 1; i < length; i++) {
          if (ch != strs[i].charAt(idx)) {
            isStop = true;
            break;
          }
        }
        if (isStop) {
          break;
        } else {
          answer.append(strs[0].charAt(idx++));
        }
      }
      return answer.toString();
    }

  }

  public static void main(String[] args) {
    String[] strs = { "technically", "technic", "technology", "technical" };
    System.out.println(new Solution().solution(strs));
  }
}
