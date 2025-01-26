package programmers.Lv2;

import java.util.HashSet;
import java.util.Set;

public class P42890_3 {

    public static void main(String[] args) {
        String[][] relation = {
            {"100", "ryan", "music", "2"},
            {"200", "apeach", "math", "2"},
            {"300", "tube", "computer", "3"},
            {"400", "con", "computer", "4"},
            {"500", "muzi", "music", "3"},
            {"600", "apeach", "music", "2"}
        };
        int solution = new Solution().solution(relation);
        System.out.println(solution);
    }

    private static class Solution {

        private final Set<String> set = new HashSet<>();
        private String[][] relation;

        public int solution(String[][] relation) {
            this.relation = relation;

            int len = relation[0].length;
            for (int size = 1; size <= len; size++) {
                comb(size, 0, new StringBuilder());
            }

            return set.size();
        }

        private void comb(int size, int idx, StringBuilder sb) {
            if (sb.length() == size) {
                if (isUniqueness(sb)) {
                    String candidate = sb.toString();
                    if (isMinimality(candidate)) {
                        set.add(candidate);
                    }
                }
                return;
            }

            for (; idx < relation[0].length; idx++) {
                sb.append(idx);
                comb(size, idx + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        private boolean isUniqueness(StringBuilder candidate) {
            int length = candidate.length();
            Set<String> set = new HashSet<>();
            for (String[] row : relation) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < length; i++) {
                    int idx = candidate.charAt(i) - '0';
                    sb.append(row[idx]);
                }

                String target = sb.toString();
                if (set.contains(target)) {
                    return false;
                } else {
                    set.add(target);
                }
            }
            return true;
        }

        private boolean isMinimality(String candidate) {
            for (String s : set) {
                int cnt = 0;
                int len = s.length();
                for (int i = 0; i < len; i++) {
                    String sub = s.substring(i, i + 1);
                    if (candidate.contains(sub)) {
                        cnt += 1;
                    }
                }
                if (cnt == len) {
                    return false;
                }
            }
            return true;
        }

    }
}
