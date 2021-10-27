package programmers.review;

import java.util.Arrays;

public class R42885 {
  static class Solution {
    public int solution(int[] people, int limit) {
      int answer = people.length;
      Arrays.sort(people);
      for (int i = people.length - 1; i > people.length - answer; i--) {
        int a = people[i];
        int num = people[people.length - answer];
        if (a + num <= limit) {
          answer--;
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] people = { 50, 50, 50, 70, 80 };
    int limit = 100;
    System.out.println(new Solution().solution(people, limit));
  }
}

// 50,50,70,80 -> 2
// 50,50,50,70,80 -> 2
// idx:2, answer: 5 ->
