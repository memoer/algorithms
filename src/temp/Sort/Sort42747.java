package temp.Sort;

import java.util.Arrays;

// n편 중, h번 이상 인용된 논문이 h편 이상 / 나머지 논문이 h번 이하 인용 -> h의 최댓값이 H-Index
public class Sort42747 {
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
    // 0,1,3,5,6
    int[] citations = { 0, 0 };
    System.out.println(new Solution().solution(citations));
  }
}
// 1, 2, 4, 5, 6