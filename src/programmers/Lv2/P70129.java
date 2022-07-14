package programmers.Lv2;

public class P70129 {
  public static void main(String[] args) {
    String s = "01110";
    for (int n : new Solution().solution(s)) System.out.println(n + ", ");
    System.out.println();
  }

  private static class Solution {
    private int rotation = 0;
    private int zeroCount = 0;

    // 1 <= s.length <= 150,000
    public int[] solution(String s) {
      while (!s.equals("1")) {
        s = extracted(s);
        rotation += 1;
      }
      return new int[]{rotation, zeroCount};
    }

    private String extracted(String s) {
      int count = 0;
      for (char ch : s.toCharArray()) {
        if (ch == '0') zeroCount += 1;
        else count += 1;
      }
      return  Integer.toBinaryString(count);
    }
  }
}
