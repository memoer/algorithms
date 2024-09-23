package programmers.highscore.greedy;

import java.util.Arrays;

public class P42885 {

  public static void main(String[] args) {
    int[] people = new int[]{70, 50, 80, 50};
    int limit = 100;
    int solution = new Solution().solution(people, limit);
    System.out.println(solution);
  }

  public static class Solution {

    public int solution(int[] people, int limit) {
      int l = 0;
      int r = people.length;
      int answer = 0;
      Arrays.sort(people);

      while (l < r) {
        int a = people[l];
        int b = people[r];
        int weight = a + b;
        if (weight == limit) {
          l += 1;
          r -= 1;
        } else if (weight > limit) {
          r -= 1;
        } else {
          int count = 1;
          while (weight + people[l + count] <= limit) {
            weight += people[l + count];
            count += 1;
          }
          l += count;
          r -= 1;
        }
        answer += 1;
      }
      return answer;
    }

  }
}
