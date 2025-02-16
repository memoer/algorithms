package programmers.Lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class P72412_3 {

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210",
            "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
            "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200",
            "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100",
            "- and - and - and - 150"};
        int[] solution = new Solution().solution(info, query);
        for (int i : solution) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static class Solution {

        private final List<String[]> data = new ArrayList<>();
        private final Map<String, List<Integer>> idxMap = new HashMap<>();

//        info len -> N, query N -> M
        public int[] solution(String[] info, String[] query) {
//            O(N)
            for (String s : info) {
                String[] split = s.split(" ");
                data.add(split);
            }
            data.sort((v1, v2) -> {
                int a = v1[0].compareTo(v2[0]);
                if (a != 0) {
                    return a;
                }
                int b = v1[1].compareTo(v2[1]);
                if (b != 0) {
                    return b;
                }
                int c = v1[2].compareTo(v2[2]);
                if (c != 0) {
                    return c;
                }
                int d = v1[3].compareTo(v2[3]);
                if (d != 0) {
                    return d;
                }
                return Integer.compare(Integer.parseInt(v1[4]), Integer.parseInt(v2[4]));
            });

            int len = query.length;
            int[] answer = new int[len];
//            O(M)
            for (int i = 0; i < len; i++) {
                String s = query[i];
                String[] conditions = Arrays.stream(s.split(" ")).filter(v -> !Objects.equals(v, "and"))
                    .toArray(String[]::new);
                answer[i] = find(conditions);
            }

            return answer;
        }

        private int find(String[] conditions) {
            List<String[]> filtered = new ArrayList<>(List.copyOf(data));
            int size = filtered.size();
            int cnt = size;

//            O(5)
            for (int i = 0; i < 5; i++) {
                String condition = conditions[i];
                boolean all = Objects.equals(condition, "-");
                if (all) {
                    continue;
                }

                for (int j = 0; j < size; j++) {
                    String[] data = filtered.get(j);
                    if (data == null) {
                        continue;
                    }

                    boolean isValid =
                        i != 4 ? data[i].equals(condition) : Integer.parseInt(data[i]) >= parse(condition);
                    if (!isValid) {
                        filtered.set(j, null);
                        cnt -= 1;
                    }
                }
            }
            return cnt;
        }

        private int parse(String str) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }
}

/**
 * [ ["java", ], [" ]
 */