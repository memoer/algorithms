package programmers.review;

import java.util.*;

public class P42890 {

    public static void main(String[] args) {
        String[][] relation = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"},
            {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"},
            {"600", "apeach", "music", "2"},};
        int solution = new Solution().solution(relation);
        System.out.println(solution);
    }

    private static class Solution {

        String[][] relation;
        List<String> list = new ArrayList<>();
        Set<String> result = new HashSet<>();

        public int solution(String[][] relation) {
            this.relation = relation;
            int len = relation[0].length;
            for (int i = 1; i <= len; i++) {
                comb(len, i, 0, new StringBuilder());
            }

            for (String key : list) {
                if (isUnique(key) && isMinimal(key)) {
                    result.add(key);
                }
            }
            return result.size();
        }

        private void comb(int n, int r, int start, StringBuilder sb) {
            if (r == sb.length()) {
                list.add(sb.toString());
                return;
            }
            for (int i = start; i < n; i++) {
                sb.append(i);
                comb(n, r, i + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        private boolean isUnique(String candidate) {
            char[] arr = candidate.toCharArray();
            Set<String> set = new HashSet<>();
            for (String[] row : this.relation) {
                StringBuilder sb = new StringBuilder();
                for (char idx : arr) sb.append(row[idx - '0']);
                boolean firstAdded = set.add(sb.toString());
                if (!firstAdded) {
                    return false;
                }
            }
            return true;
        }

        private boolean isMinimal(String candidate) {
            for (String s : result) {
                char[] arr = s.toCharArray();
                int acc = 0;
                for (char c : arr) {
                    if (candidate.contains(String.valueOf(c))) {
                        acc += 1;
                    }
                }
                if (acc == arr.length) {
                    return false;
                }
            }
            return true;
        }
    }
}