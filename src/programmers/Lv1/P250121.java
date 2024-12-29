package programmers.Lv1;

import java.util.Arrays;
import java.util.Comparator;

public class P250121 {

    public static void main(String[] args) {
        int[][] data = {
            {1, 20300104, 100, 80},
            {2, 20300804, 847, 37},
            {3, 20300401, 10, 8}
        };
        String ext = "date";
        int val_ext = 20300501;
        String sort_by = "remain";
        int[][] solution = new Solution().solution(data, ext, val_ext, sort_by);

        for (int[] ints : solution) {
            System.out.print("[");
            for (int anInt : ints) {
                System.out.printf("%d, ", anInt);
            }
            System.out.println("]");
        }
        System.out.println();
    }

    private static class Solution {

        public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
            int findIdx = getIdx(ext);
            int sortIdx = getIdx(sort_by);
            return Arrays.stream(data)
                .filter(v -> v[findIdx] < val_ext)
                .sorted(Comparator.comparingInt(o -> o[sortIdx]))
                .toArray(int[][]::new);
        }

        private int getIdx(String ext) {
            return switch (ext) {
                case "code" -> 0;
                case "date" -> 1;
                case "maximum" -> 2;
                case "remain" -> 3;
                default -> throw new IllegalStateException("Unexpected value: " + ext);
            };
        }
    }
}
