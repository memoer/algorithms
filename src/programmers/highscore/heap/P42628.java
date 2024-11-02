package programmers.highscore.heap;

import java.util.ArrayList;
import java.util.List;

public class P42628 {

    public static void main(String[] args) {
        String[] operations = {
            "I 10", "I 20", "D 1", "I 30", "I 40", "D -1", "D -1"
        };
        int[] solution = new Solution().solution(operations);

        for (int i : solution) {
            System.out.printf("%d, ", i);
        }
        System.out.println();
    }

    private static class Solution {

        public int[] solution(String[] operations) {
            List<Integer> list = new ArrayList<>();

            for (String operation : operations) {
                String[] s = operation.split(" ");
                String cmd = s[0];
                int num = Integer.parseInt(s[1]);

                if (cmd.equals("I")) {
                    list.add(num);
                } else if (num == -1 && !list.isEmpty()) {
                    list.remove(0);
                } else if (num == 1 && !list.isEmpty()) {
                    list.remove(list.size() - 1);
                }

                if (!list.isEmpty()) {
                    list.sort(Integer::compare);
                }
            }

            if (list.isEmpty()) {
                return new int[]{0, 0};
            } else {
                return new int[]{list.get(list.size() - 1), list.get(0)};
            }
        }

    }
}
