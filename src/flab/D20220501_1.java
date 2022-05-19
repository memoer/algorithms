package flab;

// https://leetcode.com/problems/first-bad-version/
public class D20220501_1 {
  public static void main(String[] args) {
    System.out.println(new Solution().firstAdVersion(5));
    //    System.out.println(new Solution().firstAdVersion(2_126_753_390));
  }

  static class Solution {
    public boolean isBadVersion(int version) {
      //      return version >= 1702766719;
      return version >= 4;
    }

    public int firstAdVersion(int n) {
      int left = 0;
      int right = n;
      int answer = -1;
      while (left <= right) {
        int mid = (int) (Long.sum(left, right) / 2);
        if (this.isBadVersion(mid)) {
          right = mid - 1;
          answer = mid;
        } else {
          left = mid + 1;
        }
      }
      return answer;
    }
  }
}
