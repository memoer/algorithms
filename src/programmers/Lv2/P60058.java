package programmers.Lv2;

public class P60058 {
  static class Solution {
    public String solution(String p) {
      return dfs(new StringBuilder(p)).toString();
    }

    private void divide(StringBuilder sb, StringBuilder u, StringBuilder v) {
      int[] acc = new int[2];
      int idx = 0;
      for (; idx < sb.length(); idx++) {
        char ch = sb.charAt(idx);
        u.append(ch);
        acc[ch - 40] += 1;
        if (acc[0] == acc[1]) {
          break;
        }
      }
      v.append(sb.substring(idx + 1));
    }

    private StringBuilder process(StringBuilder u) {
      u.deleteCharAt(0);
      u.deleteCharAt(u.length() - 1);
      for (int i = 0; i < u.length(); i++) {
        u.setCharAt(i, u.charAt(i) == '(' ? ')' : '(');
      }
      return u;
    }

    private StringBuilder dfs(StringBuilder sb) {
      if (sb.length() == 0) {
        return new StringBuilder();
      }
      StringBuilder u = new StringBuilder();
      StringBuilder v = new StringBuilder();
      // 1. 두 문자열 u,v 로 나눈다.
      divide(sb, u, v);
      if (sb.charAt(0) == '(') {
        // u가 올바른 괄호 문자열이라면, 그대로 둔다.
        return u.append(dfs(v));
      }
      // u가 올바른 괄호 문자열이 아니라면,
      return new StringBuilder("(" + dfs(v) + ")" + process(u));
    }
  }

  public static void main(String[] args) {
    String p = ")(";
    System.out.println(new Solution().solution(p));
  }
}
