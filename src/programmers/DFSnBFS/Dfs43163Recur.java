package programmers.DFSnBFS;

public class Dfs43163Recur {
  public static void main(String[] args) {
    String begin = "hit";
    String target = "cog";
    String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };
    System.out.println(new Solution().solution(begin, target, words));
  }

  static class Solution {
    private int result = Integer.MAX_VALUE;
    private String target;
    private String[] words;
    private int wordLength;
    private int arrLength;

    private boolean isInclude() {
      for (String word : words) {
        if (word.equals(target)) {
          return true;
        }
      }
      return false;
    }

    private void dfs(String changed, boolean[] visited, int acc) {
      if (changed.equals(target)) {
        this.result = Math.min(this.result, acc);
        return;
      }
      for (int i = 0; i < arrLength; i++) {
        String word = words[i];
        if (!visited[i] && isAvailable(changed, word)) {
          visited[i] = true;
          dfs(word, visited, acc + 1);
          visited[i] = false;
        }
      }
    }

    private boolean isAvailable(String src, String dest) {
      int diff = 0;
      for (int i = 0; i < wordLength; i++) {
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
      this.target = target;
      this.words = words;
      this.wordLength = begin.length();
      this.arrLength = words.length;
      if (!isInclude()) {
        return 0;
      }
      dfs(begin, new boolean[words.length], 0);
      return this.result;
    }
  }
}
