package programmers.Lv1;

public class P68935 {
  static class Solution {
    public int solution(int n) {
      StringBuilder sb = new StringBuilder(Integer.toString(n, 3));
      sb.reverse();
      return Integer.parseInt(sb.toString(), 3);
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().solution(45));
  }
}
