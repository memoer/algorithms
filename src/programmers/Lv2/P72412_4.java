package programmers.Lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class P72412_4 {

    public static void main(String[] args) {
        String[] info = {
            "java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150",
            "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"
        };
        String[] query = {
            "java and backend and junior and pizza 100", "python and frontend and senior and chicken 200",
            "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100",
            "- and - and - and - 150"
        };
        int[] solution = new Solution().solution(info, query);
        for (int i : solution) {
            System.out.print(i + " ");
        }
        System.out.println();

    }

    private static class Solution {

        Map<String, List<Integer>> map = new HashMap<>();
        Map<String, Boolean> sorted = new HashMap<>();

        public int[] solution(String[] info, String[] query) {
            for (String s : info) {
                combination(s);
            }

            int len = query.length;
            int[] answer = new int[len];
            for (int i = 0; i < len; i++) {
                String[] parse = parse(query[i]);
                String condition = parse[0];
                List<Integer> list = map.get(condition);
                if(list == null) {
                    continue;
                }

                if (!sorted.get(condition)) {
                    sorted.put(condition, true);
                    list.sort(Integer::compare);
                }

                int score = Integer.parseInt(parse[1]);
                answer[i] = list.size() - bisearch(list, score);
            }

            return answer;
        }

        private void combination(String s) {
            String[] split = s.split(" ");
            String[] s1 = {split[0], "-"};
            String[] s2 = {split[1], "-"};
            String[] s3 = {split[2], "-"};
            String[] s4 = {split[3], "-"};
            int score = Integer.parseInt(split[4]);
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        for (int l = 0; l < 2; l++) {
                            String target = s1[i] + s2[j] + s3[k] + s4[l];

                            if (!map.containsKey(target)) {
                                map.put(target, new ArrayList<>());
                                sorted.put(target, false);
                            }
                            map.get(target).add(score);
                        }
                    }
                }
            }
        }

        private String[] parse(String s) {
            String[] arr = Arrays.stream(s.split(" ")).filter(v -> !Objects.equals(v, "and")).toArray(String[]::new);
            String score = Objects.equals(arr[4], "-") ? "0" : arr[4];
            return new String[]{arr[0] + arr[1] + arr[2] + arr[3], score};
        }


        private int bisearch(List<Integer> list, int target) {
            int l = 0;
            int r = list.size() - 1;

            if (list.get(r) < target) {
                return list.size();
            }

            while (l <= r) {
                int mid = (l + r) / 2;
                int data = list.get(mid);
                if (target <= data) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }
    }
}
