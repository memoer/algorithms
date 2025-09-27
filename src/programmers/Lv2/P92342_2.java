package programmers.Lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P92342_2 {

    public static void main(String[] args) {
        int n = 10;
        int[] info = {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3};
        int[] solution = new Solution().solution(n, info);
        for (int i : solution) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    private static class Solution {

        private int[] info;
        private final Map<Integer, List<int[]>> map = new HashMap<>();
        private int topDiff = Integer.MIN_VALUE;

        public int[] solution(int n, int[] info) {
            this.info = info;
            dfs(n, 0, new int[11]);
            return getAnswer();
        }

        private void dfs(int rest, int start, int[] temp) {
            if (rest == 0) {
                int diff = diff(this.info, temp);
                if (diff > 0) {
                    if (!map.containsKey(diff)) {
                        map.put(diff, new ArrayList<>());
                    }
                    map.get(diff).add(Arrays.copyOf(temp, 11));
                    topDiff = Math.max(topDiff, diff);
                }
            } else if (rest < 0) {
                throw new RuntimeException();
            }

            for (int i = start; i < 11; i++) {
                int n = this.info[i] >= rest ? rest : this.info[i] + 1;
                temp[i] = n;
                dfs(rest - n, i + 1, temp);
                temp[i] = 0;
            }
        }

        private int diff(int[] apeach, int[] ryan) {
            int s1 = 0;
            int s2 = 0;
            for (int i = 0; i < 11; i++) {
                int v1 = apeach[i];
                int v2 = ryan[i];
                if (v1 == 0 && v2 == 0) {

                } else if (v1 >= v2) {
                    s1 += (10 - i);
                } else {
                    s2 += (10 - i);
                }
            }
            return s2 - s1;
        }

        private int[] getAnswer() {
            if (this.map.isEmpty()) {
                return new int[]{-1};
            }

            List<int[]> answers = this.map.get(topDiff);
            int size = answers.size();
            if (size == 1) {
                return answers.get(0);
            }

            int[] answer = null;
            for (int i = 10; i >= 0; i--) {
                for (int[] target : answers) {
                    if (target[i] == 0) {
                        continue;
                    }

                    if (answer == null) {
                        answer = target;
                    } else if (answer[i] < target[i]) {
                        answer = target;
                    }
                }
                if (answer != null) {
                    break;
                }
            }
            return answer;
        }
    }
}
