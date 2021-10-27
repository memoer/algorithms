package programmers.Greedy;

import java.util.Arrays;

public class G42885 {
  static class Solution {
    public int solution(int[] people, int limit) {
      int answer = 0;
      int front = 0;
      int back = people.length - 1;
      Arrays.sort(people);
      while (front <= back) {
        if (front == back) {
          answer++;
          break;
        } else if (people[back] <= limit / 2) {
          int rest = back - front + 1;
          answer += rest / 2 + (rest % 2 == 0 ? 0 : 1);
          break;
        } else if (people[front] + people[back] <= limit) {
          front++;
        }
        back--;
        answer++;
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] people = { 70, 80, 50 };
    int limit = 100;
    System.out.println(new Solution().solution(people, limit));
  }
}
