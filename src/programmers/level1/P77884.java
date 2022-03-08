package programmers.level1;

public class P77884 {
  static class Solution {

    public int solution(int left, int right) {
      int answer = 0;
      for (int i = left; i <= right; i++) {
        if (i % Math.sqrt(i) == 0) {
          // 제곱 수인 경우, 약수의 개수가 홀수
          answer -= i;
        } else {
          // 제곱 수가 아닌 경우, 약수의 개수가 짝수
          answer += i;
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int left = 13;
    int right = 17;
    System.out.println(new Solution().solution(left, right));
  }
}
