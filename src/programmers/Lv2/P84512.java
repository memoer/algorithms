package programmers.Lv2;

import java.util.*;

public class P84512 {
  public static void main(String[] args) {
    String[] words = {"AAAAE", "AAAE", "I", "EIO"};
    for (String word : words) System.out.println(new Solution().solution(word));
  }

  private static class Solution {
    private final List<String> LIST = new ArrayList<>();
    private final char[] ARR = {'A', 'E', 'I', 'O', 'U'};

    /*
     * 1 <= word <= 5
     * word -> A, E, I, O, U
     */
    public int solution(String word) {
      permutation(new StringBuilder(), 0);
      Collections.sort(LIST);
      return findIdx(word);
    }


    private void permutation(StringBuilder sb, int acc) {
      LIST.add(sb.toString());
      if (acc == 5) return;
      for (char c : ARR) {
        sb.append(c);
        permutation(sb, acc + 1);
        sb.deleteCharAt(acc);
      }
    }

    private int findIdx(String word) {
      Collections.sort(LIST);
      int left = 0;
      int right = LIST.size() - 1;
      while (left <= right) {
        int mid = (left + right) / 2;
        int compare = word.compareTo(LIST.get(mid));
        if (compare > 0) {
          left = mid + 1;
        } else if (compare < 0) {
          right = mid - 1;
        } else {
          return mid;
        }
      }
      throw new IllegalArgumentException();
    }
  }
}
