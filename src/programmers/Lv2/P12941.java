package programmers.Lv2;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class P12941 {
  public static void main(String[] args) {
    int[] A = {1, 2};
    int[] B = {3, 4};
    System.out.println(new Solution().solution(A, B));
  }

  private static class Solution {
    // 1 <= 배열 A,B의 크기 <= 1,000
    public int solution(int[] A, int[] B) {
      int length = A.length;
      int answer = 0;
      Arrays.sort(A);
      Arrays.sort(B);
      for (int i = 0; i < length; i++) answer += (A[i] * B[length - i - 1]);
      return answer;
    }

    public int temp(int[] A, int[] B) {
      int answer = 0;
      PriorityQueue<Integer> pqA = new PriorityQueue<>(Collections.reverseOrder());
      PriorityQueue<Integer> pqB = new PriorityQueue<>();
      // NlogN
      for (int a : A) pqA.add(a);
      // NlogN
      for (int b : B) pqB.add(b);
      // NlogN
      while (!pqA.isEmpty()) answer += (pqA.poll() * pqB.poll());
      // 1. 전체 시간 복잡도 -> O(3NlogN) -> O(NlogN)
      // 2. 1,000 * log(1,000)
      return answer;
    }
  }
}
