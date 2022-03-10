package programmers.Lv1;

import java.util.ArrayList;
import java.util.List;

public class P12906 {
  static class Solution {
    public int[] solution(int[] arr) {
      List<Integer> list = new ArrayList<>();
      int pre = -1;
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] == pre) {
          continue;
        }
        pre = arr[i];
        list.add(arr[i]);
      }
      return list.stream().mapToInt(Integer::valueOf).toArray();
    }
  }

  public static void main(String[] args) {
    int[] arr = { 1, 1, 3, 3, 0, 1, 1 };
    for (int num : new Solution().solution(arr)) {
      System.out.println(num);
    }
  }
}
