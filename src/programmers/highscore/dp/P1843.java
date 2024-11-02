package programmers.highscore.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Thinking! Queue 사용시 6초나 걸림.. 왜?? Queue가 그렇게 느림?
public class P1843 {

    public static void main(String[] args) {
        String[] arr = {
            "5", "-", "3", "+", "1", "+", "2", "-", "4"
        };
        int solution = new Solution().solution(arr);
        System.out.println(solution);
    }

    private static class Solution {

        private int answer = Integer.MIN_VALUE;

        public int solution(String[] arr) {
            int len = arr.length + 1;
            List<String[]>[] list = new List[len];
            Set<String> set = new HashSet<>();
            for (int i = 0; i < arr.length + 1; i++) {
                list[i] = new ArrayList<>();
            }
            int i = 0;
            set.add(String.join("", arr));
            list[i].add(arr);

            for (; i < len; i++) {
                List<String[]> targets = list[i];
                for (String[] target : targets) {
                    int size = target.length;
                    for (int j = 0; j + 2 < size; j += 2) {
                        int n = calculate(target, j);
                        if (size == 3) {
                            answer = Math.max(answer, n);
                        } else {
                            String[] nextArr = copy(target, j, String.valueOf(n));
                            String nextStr = String.join("", nextArr);
                            if (!set.contains(nextStr)) {
                                list[i + 1].add(nextArr);
                                set.add(nextStr);
                            }
                        }
                    }
                }
            }

            return answer;
        }

        private int calculate(String[] list, int idx) {
            Integer a = Integer.valueOf(list[idx]);
            String op = list[idx + 1];
            Integer b = Integer.valueOf(list[idx + 2]);

            if (op.equals("+")) {
                return a + b;
            } else {
                return a - b;
            }
        }

        private String[] copy(String[] list, int idx, String n) {
            String a = list[idx];
            String b = list[idx + 1];
            String c = list[idx + 2];
            list[idx] = n;
            list[idx + 1] = null;
            list[idx + 2] = null;

            String[] result = new String[list.length - 2];
            for (int i = 0, j = 0; i < list.length; i++) {
                if (list[i] == null) {
                    continue;
                }
                result[j++] = list[i];
            }

            list[idx] = a;
            list[idx + 1] = b;
            list[idx + 2] = c;
            return result;
        }

    }
}
