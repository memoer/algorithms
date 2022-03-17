package temp.Lv2;

public class P60057 {
  static class Solution {
    public int solution(String s) {
      int answer = Integer.MAX_VALUE;
      int size = s.length();
      int len = size / 2 + 1;

      for (int slice = 1; slice <= len; slice++) {
        int cnt = 1;
        int sum = 0;
        String pre = "";
        for (int i = 0; i < size; i += slice) {
          String cur = s.substring(i, i + slice > size ? size : i + slice);
          if (pre.equals(cur)) {
            cnt += 1;
          } else {
            if (cnt != 1) {
              sum += (int) Math.log10(cnt) + 1;
            }
            sum += pre.length();
            pre = cur;
            cnt = 1;
          }
        }
        if (cnt != 1) {
          sum += (int) Math.log10(cnt) + 1;
        }
        sum += pre.length();
        answer = Math.min(answer, sum);
      }

      return answer;
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().solution("aabbaccc"));
  }
}
