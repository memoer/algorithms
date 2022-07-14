package programmers.Lv2;

public class P12980 {
  public static void main(String[] args) {
    int n = 5_000;
    System.out.println(new Solution().solution(n));
  }

  private static class Solution {
    /*
    1. 순간이동을 하면 건전지 X, 앞으로 K칸을 점프하면 건전지 K만큼 사용
      - 순간이동을 하면, "현재까지 온 거리 x 2" 로 이동한다.
    2. 슈트를 착용하고 N만큼 떨어져 있는 장소로 가려고 한다.
    3. 사용해야 하는 건전지 사용량의 최솟값을 return하라.
     */
    /*
     * 1 <= N <= 1억
     * 1 <= K
     */
    public int solution(int n) {
      int answer = 0;
      int temp = n;
      while (temp != 0) {
        if (temp % 2 != 0) {
          temp -= 1;
          answer += 1;
        }
        temp /= 2;
      }
      return answer;
    }
  }
}

/*
1 -> 5%1=0, 순간이동
2 -> 5%2=1, 순간이동
4 -> 5%4=1,
 */
