package programmers.highscore.sort;

import java.util.Arrays;

public class P42747 {

  public static void main(String[] args) {
    int[] citations = new int[]{4, 0, 6, 1, 5};
    int solution = new Solution().solution(citations);
    System.out.println(solution);
  }

  private static class Solution {

    public int solution(int[] citations) {
      int answer = 0;
      int n = citations.length;
      Arrays.sort(citations);
      for (int i = 0; i < n; i++) {
        int h = n - i;
        if (h <= citations[i]) {
          answer = Math.max(answer, h);
        }
      }
      return answer;
    }
  }

//  0,1,3,5,6
//  h = 5-4 = 1, 6

//  n편 중,
//  h편의 논문에서 h회 이상 인용되었고
//  나머지 n-h편의 논문에서 h회 이하 인용되어야 한다.

}
