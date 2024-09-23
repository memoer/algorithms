package programmers.highscore.fullsearch;

import java.util.ArrayList;
import java.util.List;

public class P84512 {

  public static void main(String[] args) {
    int solution = new Solution().solution("I");
    System.out.println(solution);
  }

  private static class Solution {

    private List<String> list = new ArrayList<>();
    private char[] charArr = new char[]{'A', 'E', 'I', 'O', 'U'};

    public int solution(String word) {
      permutation(new StringBuilder(), 0);
      return list.indexOf(word);
    }

    private void permutation(StringBuilder sb, int depth) {
      list.add(sb.toString());
      if (depth >= 5) {
        return;
      }
      for (int i = 0; i < 5; i++) {
        int len = sb.length();
        sb.append(charArr[i]);
        permutation(sb, depth + 1);
        sb.deleteCharAt(len);
      }
    }
  }
}
