package programmers.Lv2;

import java.util.HashSet;
import java.util.Set;

public class P49994_2 {

    public static void main(String[] args) {
        int solution = new Solution().solution("LULLLLLLU");
        System.out.println(solution);
    }

    private static class Solution {

        private final Set<String> visited = new HashSet<>();
        private final int[] xy = {0, 0};
        private final int MAX = 5;

        public int solution(String dirs) {
            int len = dirs.length();
            char[] arr = dirs.toCharArray();

            for (int i = 0; i < len; i++) {
                int[] where = where(arr[i]);

                int nextX = xy[0] + where[0];
                int nextY = xy[1] + where[1];
                boolean isAvailable = nextX >= -MAX && nextX <= MAX && nextY >= -MAX && nextY <= MAX;
                if (!isAvailable) {
                    continue;
                }

                String v1 = String.format("%d%d%d%d", xy[0], xy[1], nextX, nextY);
                String v2 = String.format("%d%d%d%d", nextX, nextY, xy[0], xy[1]);
                xy[0] = nextX;
                xy[1] = nextY;
                visited.add(v1);
                visited.add(v2);
            }

            return visited.size() / 2;
        }

        private int[] where(char ch) {
            return switch (ch) {
                case 'U' -> new int[]{0, 1};
                case 'D' -> new int[]{0, -1};
                case 'L' -> new int[]{-1, 0};
                case 'R' -> new int[]{1, 0};
                default -> throw new IllegalStateException("Unexpected value: " + ch);
            };
        }

    }
}
