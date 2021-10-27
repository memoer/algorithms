package programmers.search.complete;

import java.util.Arrays;

public class S42840 {
  static class Person {
    int count = 0;
    int id;

    public Person(int id) {
      this.id = id;
    }
  }

  static class Solution {

    public int[] solution(int[] answers) {
      int[] a = { 1, 2, 3, 4, 5 };
      int[] b = { 2, 1, 2, 3, 2, 4, 2, 5 };
      int[] c = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, };
      int[] score = { 0, 0, 0 };
      int[] result = new int[3];
      int curIdx = 0;
      for (int i = 0; i < answers.length; i++) {
        if (a[i % a.length] == answers[i]) {
          score[0] += 1;
        }
        if (b[i % b.length] == answers[i]) {
          score[1] += 1;
        }
        if (c[i % c.length] == answers[i]) {
          score[2] += 1;
        }
      }
      int max = Math.max(score[0], Math.max(score[1], score[2]));
      for (int i = 0; i < 3; i++) {
        if (max == score[i]) {
          result[curIdx++] = i + 1;
        }
      }
      return Arrays.copyOf(result, curIdx);
    }
  }

  public static void main(String[] args) {
    int[] answers = { 1, 3, 2, 4, 2, 2 };
  }
}
