package programmers.Lv2;

public class P60057_2 {
  public static void main(String[] args) {
    System.out.println(new Solution().solution("xxxxxxxxxxyyy"));
  }

  private static class Solution {
    public int solution(String s) {
      final int LENGTH = s.length();
      if (LENGTH == 1) return 1;
      int answer = Integer.MAX_VALUE;
      // 1 <= LENGTH <= 1,000
      for (int slice = 1; slice <= LENGTH; slice++) {
        int duplicated = 1;
        int candidate = 0;
        int rest = 0;
        String pre = null;
        // 1,000 -> 500 -> 300 -> 250 -> 200 -> ... 1
        for (int i = 0; i < LENGTH; i += slice) {
          if (i + slice > LENGTH) {
            rest = LENGTH - i;
            break;
          }
          String cur = s.substring(i, i + slice);
          if (pre == null) {
            pre = cur;
          } else if (pre.equals(cur)) {
            duplicated += 1;
          } else {
            candidate += compression(slice, duplicated);
            duplicated = 1;
            pre = cur;
          }
        }
        candidate += rest + compression(slice, duplicated);
        answer = Math.min(answer, candidate);
      }
      return answer;
    }

    private int compression(int slice, int duplicated) {
      // aaa -> 3a 처럼 압축해서 길이 반환
      if (duplicated == 1) return slice;
      return slice + (int) Math.floor(Math.log10(duplicated)) + 1;
    }
  }
}
