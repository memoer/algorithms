package programmers.Lv2;

import java.util.HashSet;
import java.util.Set;

public class P49993_2 {

    public static void main(String[] args) {
        String skill = "CBD";
        String[] trees = {
            "BACDE", "CBADF", "AECB", "BDA"
        };
        new Solution().solution(skill, trees);
    }

    private static class Solution {

        public int solution(String skill, String[] trees) {
            Set<Character> set = set(skill);

            int answer = 0;
            for (String tree : trees) {
                boolean can = true;
                int idx = 0;
                char[] arr = tree.toCharArray();
                for (char c : arr) {
                    if (!set.contains(c)) {
                        continue;
                    }
                    if (c != skill.charAt(idx)) {
                        can = false;
                        break;
                    }
                    idx += 1;
                }
                if (can) {
                    answer += 1;
                }
            }
            return answer;
        }

        private Set<Character> set(String skill) {
            char[] arr = skill.toCharArray();
            Set<Character> set = new HashSet<>();
            for (char c : arr) {
                set.add(c);
            }
            return set;
        }
    }

}
