package programmers.highscore.dfsbfs;

import java.util.Arrays;
import java.util.Objects;

public class P43163 {

    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = {
            "hot",
            "dot",
            "dog",
            "lot",
            "log",
            "cog"
        };
        int solution = new Solution().solution(begin, target, words);
        System.out.println(solution);
    }

    private static class Solution {

        private String target;
        private String[] words;
        private int n;
        private int answer = Integer.MAX_VALUE;

        public int solution(String begin, String target, String[] words) {
            boolean contains = Arrays.asList(words).contains(target);
            if (!contains) {
                return 0;
            }

            this.target = target;
            this.words = words;
            this.n = words.length;
            dfs(begin, new boolean[n], 0);

            return answer;
        }

        private void dfs(String cur, boolean[] visited, int acc) {
            if (Objects.equals(cur, target)) {
                answer = Math.min(answer, acc);
                return;
            }
            for (int i = 0; i < n; i++) {
                String word = words[i];
                if (visited[i] || Objects.equals(cur, word) || !canChange(cur, word)) {
                    continue;
                }
                visited[i] = true;
                dfs(word, visited, acc + 1);
                visited[i] = false;
            }
        }

        private boolean canChange(String src, String dest) {
            byte cnt = 1;
            int len = src.length();
            for (int i = 0; i < len; i++) {
                if (src.charAt(i) != dest.charAt(i)) {
                    cnt -= 1;
                }
                if (cnt < 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
