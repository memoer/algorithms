package programmers.highscore.greedy;

// https://school.programmers.co.kr/questions/76244
public class P42860 {

  public static void main(String[] args) {
    String name = "AABAAABAA";
    int solution = new Solution().solution(name);
    System.out.println(solution);
  }

  private static class Solution {

    public int solution(String name) {
      int answer = 0;
      int len = name.length();
      int leftRightMove = len - 1;
      for (int i = 0; i < len; i++) {
        char ch = name.charAt(i);
        answer += getTopDownMove(ch);
        int j = i + 1;
        while (j < len && name.charAt(j) == 'A') {
          j += 1;
        }
        leftRightMove = Math.min(leftRightMove, Math.min(i + i + (len - j), (len - j) + (len - j) + i));
      }
      answer += leftRightMove;
      return answer;
    }

    private int getTopDownMove(char ch) {
      if (ch == 'A') {
        return 0;
      }
      int a = ch - 'A';
      int b = 'Z' - ch + 1;
      return Integer.min(a, b);
    }
  }
}
