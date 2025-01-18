package programmers.Lv1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P258712 {

    public static void main(String[] args) {
        String[] friends = {"muzi", "ryan", "frodo", "neo"};
        String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan",
            "neo muzi"};
        int solution = new Solution().solution(friends, gifts);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(String[] friends, String[] gifts) {
            int fLen = friends.length;

            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < fLen; i++) {
                map.put(friends[i], i);
            }

            int[][] record = new int[fLen][fLen];
            int[] weight = new int[fLen];
            for (String gift : gifts) {
                String[] v = gift.split(" ");
                record[map.get(v[0])][map.get(v[1])] += 1;
                weight[map.get(v[0])] += 1;
                weight[map.get(v[1])] -= 1;
            }

            int[] nextMonth = new int[fLen];
            int rowLen = record.length;
            int colLen = record[0].length;
            for (int fromIdx = 0; fromIdx < rowLen; fromIdx++) {
                for (int toIdx = fromIdx; toIdx < colLen; toIdx++) {
                    if (fromIdx == toIdx) {
                        continue;
                    }

                    int sendCnt = record[fromIdx][toIdx];
                    int receiveCnt = record[toIdx][fromIdx];
                    if (sendCnt > receiveCnt) {
                        nextMonth[fromIdx] += 1;
                    } else if (sendCnt < receiveCnt) {
                        nextMonth[toIdx] += 1;
                    } else {
                        if (weight[fromIdx] > weight[toIdx]) {
                            nextMonth[fromIdx] += 1;
                        } else if (weight[fromIdx] < weight[toIdx]) {
                            nextMonth[toIdx] += 1;
                        }
                    }
                }
            }

            return Arrays.stream(nextMonth).max().getAsInt();
        }
    }
}
