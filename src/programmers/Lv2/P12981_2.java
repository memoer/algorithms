package programmers.Lv2;

import java.util.HashSet;
import java.util.Set;

public class P12981_2 {

    public static void main(String[] args) {
        int n = 3;
        String[] words = {
            "tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"
        };
        int[] solution = new Solution().solution(n, words);
        System.out.println(solution[0] + ", " + solution[1]);
    }

    private static class Solution {

        public int[] solution(int n, String[] words) {
            int nth = 0;
            char pre = '\0';
            Set<String> used = new HashSet<>();

            int len = words.length;
            for (int i = 0; i < len; i++) {
                nth += 1;

                String word = words[i];
                if ((pre != '\0' && pre != word.charAt(0)) || used.contains(word)) {
                    int mod = nth % n;
                    int div = nth / n;
                    return new int[]{mod != 0 ? mod : n, mod == 0 ? div : div+1};
                } else {
                    used.add(word);
                    pre = word.charAt(word.length() - 1);
                }
            }

            return new int[]{0, 0};
        }

    }
}
