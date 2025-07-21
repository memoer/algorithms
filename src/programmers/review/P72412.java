package programmers.review;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class P72412 {

    public static void main(String[] args) {
        String[] info = {
            "java backend junior pizza 150",
            "python frontend senior chicken 210",
            "python frontend senior chicken 150",
            "cpp backend senior pizza 260",
            "java backend junior chicken 80",
            "python backend senior chicken 50"
        };
        String[] query = {
            "java and backend and junior and pizza 100",
            "python and frontend and senior and chicken 200",
            "cpp and - and senior and pizza 250",
            "- and backend and senior and - 150",
            "- and - and - and chicken 100",
            "- and - and - and - 150"
        };
        int[] solution = new Solution().solution(info, query);
        for (int i : solution) {
            System.out.print(i + " ,");
        }
        System.out.println();
    }

    private static class Solution {

        Map<String, List<Integer>> map = new HashMap<>();

        public int[] solution(String[] info, String[] query) {
            for (String s : info) {
                combination(s.split(" "));
            }

            int len = query.length;
            int[] ans = new int[len];
            Set<String> set = new HashSet<>();
            for (int i = 0; i < len; i++) {
                String[] arr = convert(query[i]);
                String key = arr[0];
                int score = Integer.parseInt(arr[1]);
                List<Integer> list = map.get(key);
                if (list == null) {
                    continue;
                }

                if (!set.contains(key)) {
                    list.sort(Comparator.comparingInt(o -> o));
                    set.add(key);
                }
                ans[i] += (list.size() - binarySearch(list, score));
            }
            return ans;
        }

        private void combination(String[] split) {
            final String ALL = "-";
            String[] a = new String[]{split[0], ALL};
            String[] b = new String[]{split[1], ALL};
            String[] c = new String[]{split[2], ALL};
            String[] d = new String[]{split[3], ALL};
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        for (int m = 0; m < 2; m++) {
                            String key = a[i] + b[j] + c[k] + d[m];
                            if (!map.containsKey(key)) {
                                map.put(key, new ArrayList<>());
                            }
                            map.get(key).add(Integer.parseInt(split[4]));
                        }
                    }
                }
            }
        }

        private String[] convert(String s) {
            StringBuilder sb = new StringBuilder();
            String[] split = s.split(" and ");
            String[] last = split[3].split(" ");
            String key = sb.append(split[0]).append(split[1]).append(split[2]).append(last[0]).toString();
            return new String[]{key, last[1]};
        }

        public int binarySearch(List<Integer> list, int target) {
            int l = 0;
            int r = list.size() - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (list.get(mid) >= target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
//            mid를 기준으로 +1이 된 값이라면 `l`을 반환
//            예를들어, target이 180일 경우, [130, 170, 190]일 때에 l은 2, r은 1
            return l;
        }

    }
}