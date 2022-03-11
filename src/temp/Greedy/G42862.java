package temp.Greedy;

import java.util.Arrays;

public class G42862 {
  static class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
      int[] studentList = new int[n];
      for (int l : lost) {
        studentList[l - 1] -= 1;
      }
      for (int r : reserve) {
        studentList[r - 1] += 1;
      }
      for (int i = 0; i < n; i++) {
        if (studentList[i] != 1) {
          continue;
        }
        if (i - 1 >= 0 && studentList[i - 1] == -1) {
          studentList[i - 1] += 1;
          studentList[i] -= 1;
        } else if (i + 1 < n && studentList[i + 1] == -1) {
          studentList[i + 1] += 1;
          studentList[i] -= 1;
        }
      }
      return (int) Arrays.stream(studentList).filter(p -> p != -1).count();
    }
  }

  public static void main(String[] args) {
    int n = 5;
    int[] lost = { 4, 2 }, reserve = { 1, 3, 5 };
    System.out.println(new Solution().solution(n, lost, reserve));
  }
}
