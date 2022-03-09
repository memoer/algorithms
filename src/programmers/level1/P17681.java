package programmers.level1;

public class P17681 {
  static class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
      String[] arr = new String[n];
      for (int i = 0; i < n; i++) {
        StringBuilder sb = new StringBuilder(Integer.toString(arr1[i] | arr2[i], 2));
        int l = sb.length();
        if (l != n) {
          for (int j = 0; j < n - l; j++) {
            sb.insert(0, "0");
          }
        }
        arr[i] = sb.toString().replace("0", " ").replace("1", "#");
      }
      return arr;
    }
  }

  public static void main(String[] args) {
    int n = 6;
    int[] arr1 = { 46, 33, 33, 22, 31, 50 };
    int[] arr2 = { 27, 56, 19, 14, 14, 10 };
    // new Solution().solution(n, arr1, arr2);
    for (String s : new Solution().solution(n, arr1, arr2)) {
      System.out.println(s);
    }
  }
}
