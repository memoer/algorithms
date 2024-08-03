package programmers.highscore.hash;

import java.util.Arrays;
import java.util.HashSet;

public class P1845 {

  public static void main(String[] args) {

  }

  class Solution {

    public int solution(int[] nums) {
      int select = nums.length / 2;
      HashSet<Integer> set = new HashSet<>();
      for (int num : nums) {
        set.add(num);
      }

      int size = set.size();
      if (size > select) {
        return select;
      } else {
        return size;
      }
    }
  }
}
