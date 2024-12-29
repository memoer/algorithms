package programmers.Lv1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P340198 {

    public static void main(String[] args) {
        int[] mats = {5, 3, 2};
        String[][] park = {
            {"A", "A", "-1", "B", "B", "B", "B", "-1"},
            {"A", "A", "-1", "B", "B", "B", "B", "-1"},
            {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
            {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"},
            {"D", "D", "-1", "-1", "-1", "-1", "-1", "F"},
            {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}
        };
        int solution = new Solution().solution(mats, park);
        System.out.println(solution);
    }

    private static class Solution {

        private final Set<Integer> candidate = new HashSet<>();
        private String[][] park;
        private int width;
        private int height;

        public int solution(int[] mats, String[][] park) {
            this.park = park;
            this.width = park[0].length;
            this.height = park.length;

            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    if (!park[row][col].equals("-1")) {
                        continue;
                    }
                    check(row, col);
                }
            }

            return candidate.stream()
                .filter(v1 -> Arrays.stream(mats).anyMatch(v2 -> v1 == v2))
                .max(Comparator.comparingInt(o -> o))
                .orElse(-1);
        }

        private void check(int sRow, int sCol) {
            int max = 0;
            for (int col = sCol; col < width; col++) {
                boolean isAvailable = park[sRow][col].equals("-1");
                if (isAvailable) {
                    max += 1;
                } else {
                    break;
                }
            }
            if (max == 0) {
                return;
            }

            List<Integer> tmp = new ArrayList<>();
            int maxRow = Math.min(sRow + max, this.height);
            int maxCol = Math.min(sCol + max, this.width);
            for (int row = sRow; row < maxRow; row++) {
                int cnt = 0;
                for (int col = sCol; col < maxCol; col++) {
                    boolean isAvailable = park[row][col].equals("-1");
                    if (!isAvailable) {
                        break;
                    }
                    cnt += 1;
                }
                tmp.add(cnt);
            }

            int size = tmp.size();
            if (size == 0) {
                return;
            }

            int min = tmp.stream().min(Integer::compare).get();
            if (min <= size) {
                candidate.add(min);
            }
        }
    }
}
