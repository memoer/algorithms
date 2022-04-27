package programmers.Lv2;

import java.util.*;

public class P72412_4 {

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        for (int result : new P72412_4.Solution().solution(info, query)) System.out.print(result + ", ");
        System.out.println();
    }

    static class Solution {

        private Map<String, List<Integer>> map;
        private Map<String, Boolean> sorted;
        private int[] answer;
        private int queryLength;

        private void permutation(String info) {
            String[] splitted = info.split(" ");
            String[][] cases = new String[4][2];
            for (int i = 0; i < 4; i++) {
                cases[i][0] = splitted[i];
                cases[i][1] = "-";
            }
            for (int i = 0; i < 2; i++) {
                String a = cases[0][i];
                for (int j = 0; j < 2; j++) {
                    String b = cases[1][j];
                    for (int k = 0; k < 2; k++) {
                        String c = cases[2][k];
                        for (int l = 0; l < 2; l++) {
                            String key = a + b + c + cases[3][l];
                            if (!this.map.containsKey(key)) {
                                this.map.put(key, new ArrayList<>());
                            }
                            this.map.get(key).add(Integer.parseInt(splitted[4]));
                        }
                    }
                }
            }
        }

        private int bisearch(List<Integer> list, int target) {
            int left = 0;
            int right = list.size() - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (target > list.get(mid)) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return left;
        }

        public int[] solution(String[] info, String[] query) {
            this.queryLength = query.length;
            this.map = new HashMap<>();
            this.answer = new int[this.queryLength];
            for (String i : info) {
                permutation(i);
            }

            this.sorted = new HashMap<>(this.map.size());
            for (String key : this.map.keySet()) {
                this.sorted.put(key, false);
            }

            for (int i = 0; i < this.queryLength; i++) {
                Condition condition = Condition.getInstance(query[i]);
                if (!this.map.containsKey(condition.key)) {
                    this.answer[i] = 0;
                    continue;

                }
                List<Integer> list = map.get(condition.key);
                if (!this.sorted.get(condition.key)) {
                    this.map.get(condition.key).sort(Comparator.comparingInt(pre -> pre));
                    this.sorted.put(condition.key, true);
                }
                this.answer[i] = list.size() - bisearch(list, condition.score);
            }
            return this.answer;
        }

        private static class Condition {
            public String key;
            public int score;

            public Condition(String key, int score) {
                this.key = key;
                this.score = score;
            }

            public static Condition getInstance(String query) {
                String[] strings = Arrays.stream(query.split(" ")).filter(s -> !s.equals("and")).toArray(String[]::new);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    sb.append(strings[i]);
                }
                return new Condition(sb.toString(), Integer.parseInt(strings[4]));
            }
        }
    }
}
