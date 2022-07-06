package programmers.DFSnBFS;

import java.util.Stack;

public class Dfs43163 {
  public static void main(String[] args) {
    String begin = "hit";
    String target = "cog";
    String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };
    System.out.println(new Solution().solution(begin, target, words));
  }

  static class Solution {
    private boolean isInclude(String target, String[] words) {
      for (String word : words) {
        if (word.equals(target)) {
          return true;
        }
      }
      return false;
    }

    private boolean isAvailable(String src, String dest) {
      int length = src.length();
      int diff = 0;
      for (int i = 0; i < length; i++) {
        if (src.charAt(i) != dest.charAt(i)) {
          diff += 1;
        }
        if (diff > 1) {
          return false;
        }
      }
      return true;
    }

    public int solution(String begin, String target, String[] words) {
      if (!isInclude(target, words)) {
        return 0;
      }
      int result = 0;
      int arrLength = words.length;
      Stack<String> s = new Stack<>();
      boolean[] visited = new boolean[arrLength];
      s.add(begin);
      while (!s.isEmpty()) {
        String str = s.pop();
        if (str.equals(target)) {
          break;
        }
        result += 1;
        for (int i = 0; i < arrLength; i++) {
          if (!visited[i] && isAvailable(str, words[i])) {
            s.push(words[i]);
            visited[i] = true;
          }
        }
      }
      return result;
    }
  }
}
