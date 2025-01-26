package programmers.Lv2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P17684_2 {

    public static void main(String[] args) {
        String msg = "TOBEORNOTTOBEORTOBEORNOT";
        int[] solution = new Solution().solution(msg);
        for (int i : solution) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    private static class Solution {

        private final Map<String, Integer> map = new HashMap<>();
        private int len;
        private int next = 27;

        public int[] solution(String msg) {
            this.len = msg.length();
            for (int i = 65; i <= 90; i++) {
                map.put(String.valueOf((char) i), i - 64);
            }

            List<Integer> answer = new ArrayList<>();
            int startIdx = 0;
            while (startIdx < this.len) {
                String target = findAndPut(msg, startIdx);
                answer.add(map.get(target));
                startIdx += target.length();
            }

            return answer.stream().mapToInt(i -> i).toArray();
        }

        private String findAndPut(String msg, int startIdx) {
            int endIdx = startIdx + 1;
            while (endIdx + 1 <= this.len) {
                String target = msg.substring(startIdx, endIdx + 1);
                if (!map.containsKey(target)) {
                    break;
                }

                int next = endIdx + 1;
                if (next > this.len) {
                    break;
                } else {
                    endIdx = next;
                }
            }

            if (endIdx + 1 <= this.len) {
                String target = msg.substring(startIdx, endIdx + 1);
                map.put(target, next++);
            }

            return msg.substring(startIdx, endIdx);
        }

    }
}
