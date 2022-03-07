package programmers.greedy;

public class G42862 {
  static class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
      int[] people = new int[n];
      int answer = n;
      for (int l : lost) {
        people[l - 1]--;
      }
      for (int r : reserve) {
        people[r - 1]++;
      }
      for (int i = 0; i < people.length; i++) {
        if (people[i] == -1) {
          if (i > 0 && people[i - 1] == 1) {
            people[i - 1]--;
            people[i]++;
          } else if (i < people.length - 1 && people[i + 1] == 1) {
            people[i + 1]--;
            people[i]++;
          } else {
            answer--;
          }
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int n = 5;
    int[] lost = { 4, 2 }, reserve = { 3, 5 };
    System.out.println(new Solution().solution(n, lost, reserve));
  }
}
