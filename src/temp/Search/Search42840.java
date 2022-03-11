package temp.Search;

import java.util.Arrays;

public class Search42840 {
  static class Solution {
    public class Student {
      int idx;
      int[] checkList;
      int checkListLength;
      int score;

      public Student(int idx, int[] checkList) {
        this.idx = idx;
        this.checkList = checkList;
        this.checkListLength = checkList.length;
      }

      public void plusScore() {
        this.score += 1;
      }
    }

    public int[] solution(int[] answers) {
      int answerLength = answers.length;
      int[][] checkTemplateList = { { 1, 2, 3, 4, 5 },
          { 2, 1, 2, 3, 2, 4, 2, 5 },
          { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 } };
      Student[] studentList = new Student[3];
      for (int i = 0; i < 3; i++) {
        studentList[i] = new Student(i + 1, checkTemplateList[i]);
      }
      for (int i = 0; i < answerLength; i++) {
        for (Student p : studentList) {
          if (answers[i] == p.checkList[i % p.checkListLength]) {
            p.plusScore();
          }
        }
      }
      final int max = Math.max(studentList[0].score, Math.max(studentList[1].score, studentList[2].score));
      return Arrays.stream(studentList).filter(p -> p.score == max).sorted((pre, cur) -> {
        if (pre.score == cur.score) {
          return pre.idx - cur.idx;
        }
        return cur.score - pre.score;
      }).mapToInt(m -> m.idx).toArray();
    }
  }

  public static void main(String[] args) {
    int[] answers = { 1, 2, 3, 4, 5 };
    for (int num : new Solution().solution(answers)) {
      System.out.print(num + ", ");
    }
    System.out.println();
  }
}
