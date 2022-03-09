package programmers.level1;

public class P82612 {
  static class Solution {
    public long solution(int price, int money, int count) {
      long acc = 0;
      for (int i = 1; i <= count; i++) {
        acc += (price * i);
      }
      return Math.max(acc - money, 0);
    }
  }

  public static void main(String[] args) {
    int price = 3;
    int money = 20;
    int count = 4;
    System.out.println(new Solution().solution(price, money, count));
  }
}
