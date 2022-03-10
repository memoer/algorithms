package programmers.Lv1;

public class P86491 {
  static class Solution {
    public int solution(int[][] sizes) {
      int a = 0;
      int b = 0;
      int length = sizes.length;
      for (int i = 0; i < length; i++) {
        a = Math.max(a, sizes[i][0]);
        b = Math.max(b, sizes[i][1]);
      }
      for (int i = 0; i < length; i++) {
        int w = sizes[i][0];
        int h = sizes[i][1];
        if (w < h && b > w) {
          int temp = w;
          sizes[i][0] = h;
          sizes[i][1] = temp;
        }
      }
      a = 0;
      b = 0;
      for (int i = 0; i < length; i++) {
        a = Math.max(a, sizes[i][0]);
        b = Math.max(b, sizes[i][1]);
      }
      return a * b;
    }
  }

  public static void main(String[] args) {
    int[][] sizes = {
        { 60, 50 },
        { 30, 70 },
        { 60, 30 },
        { 80, 40 },
    };
    // int[][] sizes = {
    // { 10, 7 },
    // { 12, 3 },
    // { 8, 15 },
    // { 14, 7 },
    // { 5, 15 },
    // };
    System.out.println(new Solution().solution(sizes));
  }
}