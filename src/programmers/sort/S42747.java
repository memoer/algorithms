package programmers.sort;

import java.util.Arrays;

public class S42747 {
  static class Solution {
    public int solution(int[] citations) {
      int answer = -1;
      Arrays.sort(citations);
      for (int i = citations.length - 1; i > -1; i--) {
        if (citations[i] >= citations.length - i) {
          answer = citations.length - i;
        } else {
          break;
        }
      }

      return answer == -1 ? 0 : answer;
    }
  }

  public static void main(String[] args) {
    // 0,1,3,5,6
    int[] citations = { 3, 0, 6, 1, 5 };
    System.out.println(new Solution().solution(citations));
  }
}
