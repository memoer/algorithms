package programmers.sort;

import java.util.Arrays;

public class S42747Other {
  static class Solution {
    public int solution(int[] citations) {
      Arrays.sort(citations);
      int citationLength = citations.length;
      int answer = 0;
      while (answer < citationLength) {
        int idx = citationLength - answer - 1;
        if (citations[idx] <= answer) {
          break;
        } else {
          answer += 1;
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] citations = { 3, 0, 6, 1, 5 };
    System.out.println(new Solution().solution(citations));
  }
}
