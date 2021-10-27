package programmers.review;

import java.util.Arrays;

public class R42747 {
  static class Solution {
    public int solution(int[] citations) {
      int answer = -1;
      Arrays.sort(citations);
      for (int i = citations.length - 1; i > -1; i--) {
        int num = citations[i];
        if (num >= citations.length - i) {
          answer = citations.length - i;
        }
      }
      return answer == -1 ? 0 : answer;
    }
  }

  public static void main(String[] args) {
    int[] citations = { 0, 5, 5, 5 };
    System.out.println(new Solution().solution(citations));
  }
}

// n개의 배열중, K 번 이상 인용된 논문이 K 개 이상이여야 하고, 나머지는 K 이하여야 한다.

// arr, n개의 배열
// 오름차순 소팅
// n개의 배열중, arr[idx] 번 이상 인용된 논문이 arr[idx]개 이상이어야 하고, 나머지는 arr[idx] 이하
// n개의 배열중, arr[idx] 번 이상 인용된 논문이 arr[idx]개 이상
// 3번 이상 인용된 논문이 3개 이상