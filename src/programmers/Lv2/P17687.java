package programmers.Lv2;

public class P17687 {
  public static void main(String[] args) {
    int n = 16;
    int t = 16;
    int m = 2;
    int p = 1;
    System.out.println(new Solution().solution(n, t, m, p));
  }

  private static class Solution {
    /*
     * 2 <= n < 16    -> 진법
     * 0 < t <= 1,000 -> 미리 구할 숫자의 갯수 / 4이면 결과 값의 길이는 4
     * 2 <= m <= 100  -> 게임에 참가하는 인원
     * 1 <= p <= m    -> 튜브의 순서
     */
    public String solution(int n, int t, int m, int p) {
      StringBuilder answer = new StringBuilder();

      int num = 0;
      StringBuilder convertNumToBase = convertNumTo(num, n);
      for (int i = 1; answer.length() != t; i++) {
        i = i <= m ? i : 1;
        if (i == p) {
          // 튜브 순서
          answer.append(convertNumToBase.charAt(0));
        }

        convertNumToBase.deleteCharAt(0);
        if (convertNumToBase.length() == 0) {
          num += 1;
          convertNumToBase = convertNumTo(num, n);
        }
      }
      return answer.toString();
    }

    private StringBuilder convertNumTo(int num, int base) {
      String s = Integer.toString(num, base).toUpperCase();
      return new StringBuilder(s);
    }
  }
}
