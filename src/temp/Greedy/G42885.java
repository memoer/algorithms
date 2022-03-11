package temp.Greedy;

import java.util.Arrays;

public class G42885 {
  static class Solution {
    public int solution(int[] people, int limit) {
      int answer = 0;
      int i = 0;
      int j = 0;
      int length = people.length;
      Arrays.sort(people);
      for (i = length - 1; i >= 0; i--) {
        if (people[i] <= limit / 2) {
          break;
        }
        if (people[i] + people[j] <= limit) {
          j += 1;
        }
        answer += 1;
      }
      int rest = i + 1 - j;
      return answer += rest / 2 + rest % 2;
    }
  }

  public static void main(String[] args) {
    int[] people = { 70, 80, 50 };
    int limit = 100;
    System.out.println(new Solution().solution(people, limit));
  }
}
