package programmers.review;

public class R42840 {
  static class Solution {

    public int[] solution(int[] answers) {
      int[] a = { 1, 2, 3, 4, 5 };
      int[] b = { 2, 1, 2, 3, 2, 4, 2, 5 };
      int[] c = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, };
      int[] score = new int[3];
      for (int i = 0; i < answers.length; i++) {
        if (a[i % a.length] == answers[i]) {
          score[0]++;
        }
        if (b[i % b.length] == answers[i]) {
          score[1]++;
        }
        if (c[i % c.length] == answers[i]) {
          score[2]++;
        }
      }
      int t = 0;
      String s = "";
      int max = Math.max(score[0], Math.max(score[1], score[2]));
      for (int i = 0; i < score.length; i++) {
        if (score[i] == max) {
          t++;
          s += (i + 1);
        }
      }
      int[] result = new int[t];
      for (int i = 0; i < result.length; i++) {
        result[i] = s.charAt(i) - '0';
      }
      return result;
    }
  }

  public static void main(String[] args) {
    int[] answers = { 1, 3, 2, 4, 2 };
    for (int i : new Solution().solution(answers)) {
      System.out.println(i);
    }
  }
}
