package programmers.Lv1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P92334_2 {

    public static void main(String[] args) {
        String[] idArr = {"muzi", "frodo", "apeach", "neo"};
        String[] reportArr = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;
        int[] solution = new Solution().solution(idArr, reportArr, k);
        for (int i : solution) {
            System.out.printf("%d", i);
            System.out.println();
        }
        System.out.println();
    }

    static class Solution {

        /**
         * 각 유저는 한 번에 한 명의 유저를 신고할 수 있음. - 획수 제한 없음. - 동일한 유저에 대한 신고는 몇 번을 해도 1회로 처리 k번 이상 신고된 유저는 게시판 이용이 정지됨. - 해당 유저를
         * 신고한 모든 유저에게 정지 사실을 메일로 발송함.
         */
//    각 유저별로 처리 결과 메일을 받은 횟수를 반환
        public int[] solution(String[] idArr, String[] reportArr, int k) {
            Map<String, Set<String>> map = new HashMap<>();
            for (String s : reportArr) {
                String[] split = s.split(" ");
                String src = split[0];
                String dest = split[1];

                if (!map.containsKey(src)) {
                    map.put(src, new HashSet<>());
                }
                map.get(src).add(dest);
            }

            Map<String, Integer> cntMap = new HashMap<>();
            for (Set<String> set : map.values()) {
                for (String s : set) {
                    cntMap.put(s, cntMap.getOrDefault(s, 0) + 1);
                }
            }

            return Arrays.stream(idArr)
                .mapToInt(v -> {
                    Set<String> set = map.get(v);
                    if (set == null) {
                        return 0;
                    }

                    int acc = 0;
                    for (String s : set) {
                        if (cntMap.get(s) >= k) {
                            acc += 1;
                        }
                    }
                    return acc;
                })
                .toArray();
        }
    }
}
